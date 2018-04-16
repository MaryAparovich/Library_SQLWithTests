package by.htp.library.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import by.htp.library.bean.Book;
import by.htp.library.console.Input;
import by.htp.library.dao.BookDao;
import by.htp.library.dao.impl.util.DBConnectionHelper;

public class BookDaoDBImpl implements BookDao {
	private static final String SQL_SELECT_BOOKS = "select *from books";

	public void create(Book entity) {

	}

	@Override
	public Book read(int id) {
		return null;
	}

	@Override
	public List<Book> readByTitle(String title) {

		List<Book> listBooks = new ArrayList<>();

		Connection connection = DBConnectionHelper.connect();

		try {
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery("select *from books where title = " + "'" + title + "'");

			while (rs.next()) {
				Book book = new Book();
				book.setId(rs.getInt("id"));
				book.setTitle(rs.getString("title"));
				book.setDescription(rs.getString("description"));
				book.setAuthor(rs.getString("author"));
				System.out.println(book);
				
				listBooks.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		DBConnectionHelper.disconnect(connection);
		return listBooks;
	}

	@Override
	public List<Book> readAll() {
		List<Book> listBooks = new ArrayList<>();

		Connection connection = DBConnectionHelper.connect();

		try {
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(SQL_SELECT_BOOKS);

			while (rs.next()) {
				Book book = new Book();
				book.setId(rs.getInt("id"));
				book.setTitle(rs.getString("title"));
				book.setDescription(rs.getString("description"));
				book.setAuthor(rs.getString("author"));

				listBooks.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBConnectionHelper.disconnect(connection);
		return listBooks;

	}

	@Override
	public void delete(int id) {

	}

	@Override
	public void update(Book entity) {

	}

}
