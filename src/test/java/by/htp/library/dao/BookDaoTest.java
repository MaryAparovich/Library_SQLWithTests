package by.htp.library.dao;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import by.htp.library.bean.Book;
import by.htp.library.dao.impl.BookDaoDBImpl;
import by.htp.library.dao.impl.util.DBConnectionHelper;

public class BookDaoTest {

	private static final String TEST_TITLE = "Война";
	
	private Connection connection;
	private List<Book> expectedListBooks;
	private List<Book> expectedListBookByTitle;
	private BookDao bookDao;
	private Statement st;

	@BeforeClass
	public void initDefaultDBConnection() {
		
		connection = DBConnectionHelper.connect();
		System.out.println("Before class: connection opened");
	}

	@BeforeMethod
	public void getExpectedList() throws SQLException {

		st = connection.createStatement();
		ResultSet rs = st.executeQuery("select *from books");
		expectedListBooks = new ArrayList<>();

		while (rs.next()) {
			expectedListBooks.add(new Book());
		}
		System.out.println("BeforeMethod: actualList was recieved");
	}

	@BeforeMethod
	public void initDao() {
		
		bookDao = new BookDaoDBImpl();
	}
	
	@BeforeMethod
	public void getExpectedBookTitle() throws SQLException {
		expectedListBookByTitle = new ArrayList<>();
		st = connection.createStatement();
		ResultSet rs = st.executeQuery("select *from books where title = " + "'" + TEST_TITLE + "'");
		
		while (rs.next()) {
			Book book = new Book();
			book.setId(rs.getInt("id"));
			book.setTitle(rs.getString("title"));
			book.setDescription(rs.getString("description"));
			book.setAuthor(rs.getString("author"));
			System.out.println(book);
			
			expectedListBookByTitle.add(book);
		}
	}

	@Test
	public void testRecievedCorrectBookCount() {
		
		List<Book> actualListBooks = bookDao.readAll();
		Assert.assertEquals(actualListBooks.size(), expectedListBooks.size(), "The received count of books is not correct");
	}
	
	@Test
	public void testRecievedCorrectBooksByTitle() {
		
		List<Book> actualBookByTitle = bookDao.readByTitle(TEST_TITLE);
		Assert.assertEquals(actualBookByTitle, expectedListBookByTitle);
	}
	
	
	@Test
	public void testRecievedBookByNullTitle() {
		String title = null;
		List<Book> actualListBooks = bookDao.readByTitle(title);
		Assert.assertEquals(actualListBooks.size(), 0);
	}
	
	
	@AfterMethod
	public void cleanExpectedValues() {
		
		expectedListBooks = null;
		System.out.println("After method");
	}
	
	@AfterClass
	public void closeDBConnection() {
		
		DBConnectionHelper.disconnect(connection);
		System.out.println("AfterClass: connection closed");
	}
}
