package com.dev.webservice.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.dev.webservice.service.PostsService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class WebController {

	private PostsService postsServive;
	
	@GetMapping("/") // since spring 4.3
	public String main(Model model) {
		model.addAttribute("posts", postsServive.findAllDesc());
		return "main"; // (prefix: src/main/resources/templates, suffic: .hbs)
	}
}
