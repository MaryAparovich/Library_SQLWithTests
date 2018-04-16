package by.htp.library.console;
import java.util.List;
import by.htp.library.bean.Book;
import by.htp.library.bean.Employee;
import by.htp.library.dao.BookDao;
import by.htp.library.dao.EmployeeDao;
import by.htp.library.dao.impl.BookDaoDBImpl;
import by.htp.library.dao.impl.EmployeeDaoDBImpl;

public class MainApp {

	public static void main(String[] args) {

		BookDao bookDao = new BookDaoDBImpl();
		EmployeeDao employeeDao = new EmployeeDaoDBImpl();
		ConsolePrinter consolePrinter = new ConsolePrinter();
		
		List<Book> listBooks = bookDao.readAll();
		List<Employee> listEmployee = employeeDao.readAll();
		
		consolePrinter.printerInfo(listBooks, listEmployee);
		
		bookDao.readByTitle(Input.getBookTitle());
		employeeDao.readBySurname(Input.getEmployeeSurname());
	}
}

