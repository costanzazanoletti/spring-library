package org.generation.italy.repository;


import org.generation.italy.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

	public boolean existsByIsbn(String isbn);
	
}
