package by.htp.library.dao;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;
import static org.testng.Assert.expectThrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import by.htp.library.bean.Book;
import by.htp.library.bean.Employee;
import by.htp.library.dao.impl.BookDaoDBImpl;
import by.htp.library.dao.impl.EmployeeDaoDBImpl;
import by.htp.library.dao.impl.util.DBConnectionHelper;


public class EmployeeDaoTest {
	
	private Connection connection;
	private Connection connectionExp;
	private List<Employee> expectedListEmployees;
	private List<Employee> expectedListEmployeesBySurname;
	private EmployeeDao employeeDao;
	private static final String TEST_SURNAME = "Иванов";
//	private static final String DB_CONNECT_PROPERTY = "db_config";
	private static final String DB_CONNECT_FAKE_PROPERTY = "db_config_test";

	@BeforeClass
	public void initDefaultDBConnection() {
		
		connection = DBConnectionHelper.connect();
		System.out.println("Before class: connection opened");
	}
	
	@BeforeMethod
	public void initDao() {
		
		employeeDao = new EmployeeDaoDBImpl();
	}
	
	@BeforeMethod
	public void getExpectedList() throws SQLException {

		Statement st = connection.createStatement();
		ResultSet rs = st.executeQuery("select *from employees");
		expectedListEmployees = new ArrayList<>();

		while (rs.next()) {
			expectedListEmployees.add(new Employee());
		}
		System.out.println("BeforeMethod: expectedList was recieved");
	}
	
	@BeforeMethod
	public void getExpectedEmployeeSurname() throws SQLException {
		expectedListEmployeesBySurname = new ArrayList<>();
		Statement st = connection.createStatement();
		ResultSet rs = st.executeQuery("select *from employees where surname = " + "'" + TEST_SURNAME + "'");
		
		while (rs.next()) {
			Employee employee = new Employee();
			employee.setName(rs.getString("name"));
			employee.setSurname(rs.getString("surname"));			
			expectedListEmployeesBySurname.add(employee);
		}
	}

	/*@BeforeMethod
	public void getExpectedConnect() throws SQLException, ClassNotFoundException {
		connectionExp = null;
		
		
			ResourceBundle rb = ResourceBundle.getBundle(DB_CONNECT_PROPERTY);
			String url = rb.getString("db.url");
			String login = rb.getString("db.login");
			String pass = "1234";
			String driver = rb.getString("db.driver");
			Class.forName(driver);
			connectionExp = DriverManager.getConnection(url, login, pass);


	}*/
	
	@Test(expectedExceptions = SQLException.class)
	public void testCorrectConnect() throws ClassNotFoundException, SQLException{
		DBConnectionHelper.connect(DB_CONNECT_FAKE_PROPERTY);
	}

	@Test
	public void testRecievedCorrectEmployeeCount() {
		
		List<Employee> actualEmployees = employeeDao.readAll();
		Assert.assertEquals(actualEmployees.size(), expectedListEmployees.size(), "The received count of employees is not correct");
	}
	
	@Test
	public void testRecievedCorrectEmployeesBySurname() {
		
		List<Employee> actualEmployeesbySurname = employeeDao.readBySurname(TEST_SURNAME);
		Assert.assertEquals(actualEmployeesbySurname, expectedListEmployeesBySurname);
	}
	
	@AfterMethod
	public void cleanExpectedValues() {
		expectedListEmployees = null;
		System.out.println("After method");
	}
	
	@AfterClass
	public void closeDBConnection() {
		
		DBConnectionHelper.disconnect(connection);
		System.out.println("AfterClass: connection closed");
	}
	
}
