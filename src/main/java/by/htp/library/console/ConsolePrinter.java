package by.htp.library.console;

import java.util.List;
import by.htp.library.bean.Book;
import by.htp.library.bean.Employee;


public class ConsolePrinter {

	public void printerInfo(List<Book> listBooks, List<Employee> listEmployee) {

		System.out.println("Books: ");
		for (Book book : listBooks) {
			System.out.println(" " + book);
		}

		System.out.println("\nEmployees: ");
		for (Employee empl : listEmployee) {
			System.out.println(" " + empl);
		}	
	}
}
