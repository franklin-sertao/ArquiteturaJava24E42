package br.edu.infnet.franklin.controller;

import br.edu.infnet.franklin.model.domain.Embalagem;
import br.edu.infnet.franklin.service.EmbalagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/embalagens")
public class EmbalagemController {

    @Autowired
    private EmbalagemService embalagemService;

    @GetMapping
    public String lista(Model model) {
        List<Embalagem> embalagens = embalagemService.obterLista();
        model.addAttribute("embalagens", embalagens);
        return "embalagens/lista";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("embalagem", new Embalagem());
        return "embalagens/formulario";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Embalagem embalagem) {
        embalagemService.salvar(embalagem);
        return "redirect:/embalagens";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Embalagem embalagem = embalagemService.obterPorId(id);
        model.addAttribute("embalagem", embalagem);
        return "embalagens/formulario";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        embalagemService.excluir(id);
        return "redirect:/embalagens";
    }
}
