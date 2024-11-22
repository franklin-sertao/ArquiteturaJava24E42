package br.edu.infnet.franklin.controller;

import br.edu.infnet.franklin.model.domain.Receita;
import br.edu.infnet.franklin.service.ReceitaService;
import jakarta.validation.Valid;
import br.edu.infnet.franklin.service.IngredienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/receitas")
public class ReceitaController {

    @Autowired
    private ReceitaService receitaService;

    @Autowired
    private IngredienteService ingredienteService;

    @GetMapping
    public String lista(Model model) {
        model.addAttribute("receitas", receitaService.obterLista());
        return "receitas/lista";
    }

    @GetMapping("/novo")
    public String showFormNovo(Model model) {
		model.addAttribute("isNew", true);
        model.addAttribute("receita", new Receita());
        model.addAttribute("ingredientes", ingredienteService.obterLista());
        return "receitas/formulario";
    }

	@PostMapping("/novo")
    public String novo(@Valid @ModelAttribute Receita receita, BindingResult result, Model model, @RequestParam Map<String, String> params) {
		if (result.hasErrors()) {
			model.addAttribute("isNew", true);
			model.addAttribute("receita", receita);
			model.addAttribute("ingredientes", ingredienteService.obterLista());
			return "receitas/formulario";
		}

		return this.salvar(receita, params);
    }

    public String salvar(Receita receita, Map<String, String> params) {

    	// Extrai ingredientes e quantidades usando índices de array
    	Map<Long, Double> ingredientesQuantidade = params.entrySet().stream()
            .filter(e -> e.getKey().matches("ingredientes\\[\\d+\\]\\.id"))
            .collect(
                    java.util.stream.Collectors.toMap(
                            e -> Long.parseLong(params.get(e.getKey())),
                            e -> Double.parseDouble(params.get(e.getKey().replace(".id", ".quantidade")))
                    )
            );
							

        receitaService.salvar(receita, ingredientesQuantidade);
        return "redirect:/receitas";
    }

    @GetMapping("/{id}/editar")
    public String showFormEditar(@PathVariable Long id, Model model) {
        Receita receita = receitaService.obterPorId(id);
		model.addAttribute("isNew", false);
        model.addAttribute("receita", receita);
        model.addAttribute("ingredientes", ingredienteService.obterLista());
        return "receitas/formulario";
    }

	@PostMapping("/{id}/editar")
    public String editar(@Valid @ModelAttribute Receita receita, BindingResult result, Model model, @PathVariable Long id, @RequestParam Map<String, String> params) {
		if (result.hasErrors()) {
			model.addAttribute("isNew", false);
			model.addAttribute("receita", receita);
			model.addAttribute("ingredientes", ingredienteService.obterLista());

        	return "receitas/formulario";
		}

		return this.salvar(receita, params);
    }

    @GetMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id) {
        receitaService.excluir(id);
        return "redirect:/receitas";
    }
}
