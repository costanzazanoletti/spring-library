package org.generation.italy.service;

import java.time.LocalDateTime;
import java.util.List;

import org.generation.italy.model.Book;
import org.generation.italy.model.Category;
import org.generation.italy.repository.BookRepository;
import org.generation.italy.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class BookService {

	@Autowired
	private BookRepository repository;
	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<Book> findAllSortBy(String attribute){
		return repository.findAll(Sort.by(attribute));
	}
	
	public List<Book> findAllSortByRecent(){
		return repository.findAll(Sort.by("createdAt"));
	}
	
	public Book getById(Integer id) {
		return repository.getById(id);
	}
	
	public List<Category> getAllCategories(){
		return categoryRepository.findAll(Sort.by("category"));
	}
	
	public Book createBook(Book book) {
		book.setCreatedAt(LocalDateTime.now());
		return repository.save(book);
	}
	
	public Book updateBook(Book book) {
		return repository.save(book);
	}
	
	public void deleteBook(Integer id) {
		repository.deleteById(id);
	}
}
