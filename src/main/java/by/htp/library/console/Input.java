package by.htp.library.console;

import java.util.Scanner;

public class Input {

	public static Scanner scanner = new Scanner(System.in);

	public static String getBookTitle() {
		System.out.println("\nВведите название книги:");
		String title = scanner.next();
		return title;
	}
	
	public static String getEmployeeSurname() {
		System.out.println("\nВведите фамилию сотрудника:");
		String title = scanner.next();
		return title;
	}
}
