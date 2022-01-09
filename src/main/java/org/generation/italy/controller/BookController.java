package org.generation.italy.controller;

import java.util.HashMap;

import javax.validation.Valid;

import org.generation.italy.model.Book;
import org.generation.italy.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/books")
public class BookController {
	
	@Autowired
	private BookService service;
	
	@GetMapping
	public String list(Model model) {
		model.addAttribute("list", service.findAllSortByRecent());
		return "/books/list";
	}
	
	@GetMapping("/{id}")
	public String detail(Model model, @PathVariable Integer id) {
		model.addAttribute("book", service.getById(id));
		return "/books/detail";
	}
	
	@GetMapping("/create")
	public String create(Model model) {
		Book book = new Book();
		model.addAttribute("book", book);
		model.addAttribute("categories", service.getAllCategories());
		model.addAttribute("edit", false);
		return "/books/edit";
	}
	
	@PostMapping("/create")
	public String doCreateBook(@Valid @ModelAttribute("book") Book formBook, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("categories", service.getAllCategories());
			model.addAttribute("edit", false);
			return "books/edit";
		}
		service.createBook(formBook);
		
		redirectAttributes.addAttribute("message", "Book created!");
		return "redirect:/books";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(Model model, @PathVariable Integer id) {
		Book book = service.getById(id);
		model.addAttribute("book", book);
		model.addAttribute("categories", service.getAllCategories());
		model.addAttribute("edit", true);
		return "/books/edit";
	}
	
	@PostMapping("/edit/{id}")
	public String doEditBook(@Valid @ModelAttribute("book") Book formBook, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("categories", service.getAllCategories());
			model.addAttribute("edit", true);
			return "books/edit";
		}
		service.updateBook(formBook);
		redirectAttributes.addFlashAttribute("message", "Book updated!");
		return "redirect:/books";
	}
	
	@GetMapping("delete/{id}")
	public String delete(Model model, @PathVariable Integer id, RedirectAttributes redirectAttributes) {
		service.deleteBook(id);
		redirectAttributes.addAttribute("message", "Book deleted!");
		return "redirect:/books";
	}
	
}
