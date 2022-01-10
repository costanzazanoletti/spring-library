package org.generation.italy.controller;

import javax.validation.Valid;


import org.generation.italy.model.Category;
import org.generation.italy.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	private CategoryRepository repo;
	
	@GetMapping
	public String list(Model model) {
		model.addAttribute("list", repo.findAll(Sort.by("category")));
		model.addAttribute("categoryObj", new Category());
		return "/categories/categories";
	}
	
	@PostMapping("/create")
	public String doCreate(@Valid @ModelAttribute("categoryObj") Category formCategory, BindingResult bindingResult, Model model) {
		if(!bindingResult.hasErrors()) {
			repo.save(formCategory);
		}
		
		return "redirect:/categories";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id, Model model) {
		repo.deleteById(id);
		return "redirect:/categories";
	}
}
