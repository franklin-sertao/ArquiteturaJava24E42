package br.edu.infnet.franklin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.infnet.franklin.model.domain.Embalagem;
import br.edu.infnet.franklin.service.EmbalagemService;

@Controller
@RequestMapping("/embalagens")
public class EmbalagemController {

    @Autowired
    private EmbalagemService embalagemService;

    @GetMapping
    public String lista(Model model) {
        model.addAttribute("embalagens", embalagemService.obterLista());
        return "embalagens/lista";
    }

    @GetMapping("/novo")
    public String nova(Model model) {
        model.addAttribute("embalagem", new Embalagem());
        return "embalagens/formulario";
    }

    @PostMapping("/salvar")
    public String salvar(@RequestParam(required = false) Long id, @ModelAttribute Embalagem embalagem) {
        embalagemService.salvar(id, embalagem);
        return "redirect:/embalagens";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model) {
        Embalagem embalagem = embalagemService.obterPorId(id);
        model.addAttribute("embalagem", embalagem);
        return "embalagens/formulario";
    }

    @GetMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id) {
        embalagemService.excluir(id);
        return "redirect:/embalagens";
    }
}
