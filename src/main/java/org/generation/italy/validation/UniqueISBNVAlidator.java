package org.generation.italy.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


import org.generation.italy.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueISBNVAlidator implements ConstraintValidator<UniqueISBN, String>{

	@Autowired
	private BookRepository repository;
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return !repository.existsByIsbn(value);
		
	}

}
