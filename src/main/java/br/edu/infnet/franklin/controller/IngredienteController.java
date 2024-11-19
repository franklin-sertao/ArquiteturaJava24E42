package br.edu.infnet.franklin.controller;

import br.edu.infnet.franklin.model.domain.Ingrediente;
import br.edu.infnet.franklin.model.domain.IngredienteLiquido;
import br.edu.infnet.franklin.model.domain.IngredienteSeco;
import br.edu.infnet.franklin.model.domain.IngredienteUnitario;
import br.edu.infnet.franklin.service.IngredienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/ingredientes")
public class IngredienteController {

    @Autowired
    private IngredienteService ingredienteService;

    @GetMapping
    public String lista(Model model) {
        List<Ingrediente> ingredientes = ingredienteService.obterLista();
        model.addAttribute("ingredientes", ingredientes);
        return "ingredientes/lista";
    }

	@GetMapping("/novo")
	public String novo(Model model) {
		model.addAttribute("ingredienteSeco", new IngredienteSeco());
		model.addAttribute("ingredienteLiquido", new IngredienteLiquido());
		model.addAttribute("ingredienteUnitario", new IngredienteUnitario());
		return "ingredientes/formulario";
	}


    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Ingrediente ingrediente) {
        ingredienteService.incluir(ingrediente);
        return "redirect:/ingredientes";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Ingrediente ingrediente = ingredienteService.obterPorId(id);
        model.addAttribute("ingrediente", ingrediente);
        return "ingredientes/formulario";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        ingredienteService.excluir(id);
        return "redirect:/ingredientes";
    }
}
