package by.htp.library.dao;

import java.util.List;

import by.htp.library.bean.Book;

public interface BookDao extends BaseDao<Book>{

	List<Book> readByTitle(String title);
}
