package br.edu.infnet.franklin.controller;

import br.edu.infnet.franklin.model.domain.Receita;
import br.edu.infnet.franklin.model.domain.ReceitaIngrediente;
import br.edu.infnet.franklin.service.ReceitaService;
import br.edu.infnet.franklin.service.IngredienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public String novo(Model model) {
        model.addAttribute("receita", new Receita());
        model.addAttribute("ingredientes", ingredienteService.obterLista());
        return "receitas/formulario";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Receita receita,
                         @RequestParam Map<Long, Double> ingredientesQuantidade) {
        receitaService.salvar(receita, ingredientesQuantidade);
        return "redirect:/receitas";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Receita receita = receitaService.obterPorId(id);
        model.addAttribute("receita", receita);
        model.addAttribute("ingredientes", ingredienteService.obterLista());
        return "receitas/formulario";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        receitaService.excluir(id);
        return "redirect:/receitas";
    }
}
