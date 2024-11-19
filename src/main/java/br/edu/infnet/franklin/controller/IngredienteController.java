package br.edu.infnet.franklin.controller;

import br.edu.infnet.franklin.model.domain.*;
import br.edu.infnet.franklin.service.IngredienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
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
        model.addAttribute("ingrediente", new IngredienteSeco()); // Por padrão, usando IngredienteSeco
        return "ingredientes/formulario";
    }

    @PostMapping("/salvar")
    public String salvar(@RequestParam String tipoIngrediente,
                         @RequestParam String nome,
                         @RequestParam BigDecimal precoTotal,
                         @RequestParam(required = false) Boolean organico,
                         @RequestParam(required = false) Integer pesoLiquidoEmGramas,
                         @RequestParam(required = false) Integer volumeLiquidoEmML,
                         @RequestParam(required = false) Integer quantidadeUnidades) {

        Ingrediente ingrediente;

        switch (tipoIngrediente.toLowerCase()) {
            case "seco":
                IngredienteSeco seco = new IngredienteSeco();
                seco.setPesoLiquidoEmGramas(pesoLiquidoEmGramas);
                ingrediente = seco;
                break;
            case "liquido":
                IngredienteLiquido liquido = new IngredienteLiquido();
                liquido.setVolumeLiquidoEmML(volumeLiquidoEmML);
                ingrediente = liquido;
                break;
            case "unitario":
                IngredienteUnitario unitario = new IngredienteUnitario();
                unitario.setQuantidadeUnidades(quantidadeUnidades);
                ingrediente = unitario;
                break;
            default:
                throw new IllegalArgumentException("Tipo de ingrediente inválido: " + tipoIngrediente);
        }

        ingrediente.setNome(nome);
        ingrediente.setPrecoTotal(precoTotal);
        ingrediente.setOrganico(organico != null && organico);

        ingredienteService.salvar(ingrediente);
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
