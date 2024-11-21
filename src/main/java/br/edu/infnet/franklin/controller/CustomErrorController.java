package br.edu.infnet.franklin.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {
	// Define a rota para a página de erro
	
	@GetMapping("/error")
	public String error() {
		return "erro";
	}
}
