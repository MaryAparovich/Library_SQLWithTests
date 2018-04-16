package by.htp.library.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.htp.library.bean.Employee;
import by.htp.library.dao.EmployeeDao;
import by.htp.library.dao.impl.util.DBConnectionHelper;

public class EmployeeDaoDBImpl implements EmployeeDao {
	private static final String SQL_SELECT_EMPLOYEE = "select *from employees";

	public void create(Employee entity) {

	}

	public Employee read(int id) {
		return null;
	}

	public List<Employee> readBySurname(String surname) {

		List<Employee> listEmployee = new ArrayList<>();

		Connection connection = DBConnectionHelper.connect();

		try {
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery("select *from employees where surname =" + "'" + surname +"'");

			while (rs.next()) {
				Employee employee = new Employee();
				employee.setName(rs.getString("name"));
				employee.setSurname(rs.getString("surname"));
				System.out.println(employee);
				listEmployee.add(employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listEmployee;
	}

	public List<Employee> readAll() {

		List<Employee> listEmployee = new ArrayList<>();

		Connection connection = DBConnectionHelper.connect();

		try {
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(SQL_SELECT_EMPLOYEE);

			while (rs.next()) {
				Employee employee = new Employee();
				employee.setName(rs.getString("name"));
				employee.setSurname(rs.getString("surname"));

				listEmployee.add(employee);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		DBConnectionHelper.disconnect(connection);
		return listEmployee;
	}

	public void delete(int id) {

	}

	public void update(Employee entity) {

	}

}
