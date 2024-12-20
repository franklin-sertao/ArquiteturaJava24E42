package br.edu.infnet.franklin.controller;
import br.edu.infnet.franklin.model.domain.*;
import br.edu.infnet.franklin.model.dto.IngredienteForm;
import br.edu.infnet.franklin.service.IngredienteService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping("/ingredientes")
public class IngredienteController {

    @Autowired
    private IngredienteService ingredienteService;

    @GetMapping
    public String lista(Model model) {
        Iterable<Ingrediente> ingredientes = ingredienteService.obterLista();
        model.addAttribute("ingredientes", ingredientes);
        return "ingredientes/lista";
    }

    @GetMapping("/novo")
    public String showFormNovo(Model model) {
        model.addAttribute("ingredienteForm", new IngredienteForm());
        model.addAttribute("tipos", List.of("seco", "liquido", "unitario"));
		model.addAttribute("isNew", true);
        return "ingredientes/formulario";
    }

	@PostMapping("/novo")
	public String novo(@Valid @ModelAttribute IngredienteForm ingredienteForm, BindingResult result, Model model){
		if(result.hasErrors()){
			model.addAttribute("ingredienteForm", ingredienteForm);
			model.addAttribute("tipos", List.of("seco", "liquido", "unitario"));
			model.addAttribute("isNew", true);
			return "ingredientes/formulario";
		}

		return this.salvar(ingredienteForm);
	}
	
	public String salvar(@ModelAttribute IngredienteForm ingredienteForm) {
		Ingrediente ingrediente;

		if (ingredienteForm.getId() != null) {
			// Buscar ingrediente existente para edição
			ingrediente = ingredienteService.obterPorId(ingredienteForm.getId());
			if (ingrediente == null) {
				// Caso o ID seja inválido, tratar adequadamente (opcional)
				return "redirect:/ingredientes";
			}
		} else {
			// Criar novo ingrediente
			switch (ingredienteForm.getTipo().toLowerCase()) {
				case "liquido":
					ingrediente = new IngredienteLiquido();
					break;
				case "unitario":
					ingrediente = new IngredienteUnitario();
					break;
				case "seco":
				default:
					ingrediente = new IngredienteSeco();
					break;
			}
		}

		// Atualizar os campos comuns
		ingrediente.setNome       (ingredienteForm.getNome());
		ingrediente.setPrecoTotal (ingredienteForm.getPrecoTotal());
		ingrediente.setOrganico   (ingredienteForm.isOrganico());

		// Atualizar campos específicos
		if (ingrediente instanceof IngredienteSeco) {
			((IngredienteSeco) ingrediente).setPesoLiquidoEmGramas(ingredienteForm.getPesoLiquidoEmGramas());
		} else if (ingrediente instanceof IngredienteLiquido) {
			((IngredienteLiquido) ingrediente).setVolumeLiquidoEmML(ingredienteForm.getVolumeLiquidoEmML());
		} else if (ingrediente instanceof IngredienteUnitario) {
			((IngredienteUnitario) ingrediente).setQuantidadeUnidades(ingredienteForm.getQuantidadeUnidades());
		}

		ingredienteService.salvar(ingrediente);
		return "redirect:/ingredientes";
	}


    @GetMapping("/{id}/editar")
    public String showFormEditar(@PathVariable  Long id, Model model) {
		Ingrediente ingrediente = ingredienteService.obterPorId(id);
        
		if (ingrediente == null) {
            // Tratar caso o ingrediente não seja encontrado
            return "redirect:/ingredientes";
        }

		IngredienteForm ingredienteForm = new IngredienteForm();
		
		ingredienteForm.setId         (ingrediente.getId());
		ingredienteForm.setTipo       (ingrediente.getTipo());
		ingredienteForm.setNome       (ingrediente.getNome());
		ingredienteForm.setPrecoTotal (ingrediente.getPrecoTotal());
		ingredienteForm.setOrganico   (ingrediente.isOrganico());

		if (ingrediente instanceof IngredienteSeco) {
			ingredienteForm.setPesoLiquidoEmGramas(((IngredienteSeco) ingrediente).getPesoLiquidoEmGramas());
		} else if (ingrediente instanceof IngredienteLiquido) {
			ingredienteForm.setVolumeLiquidoEmML(((IngredienteLiquido) ingrediente).getVolumeLiquidoEmML());
		} else if (ingrediente instanceof IngredienteUnitario) {
			ingredienteForm.setQuantidadeUnidades(((IngredienteUnitario) ingrediente).getQuantidadeUnidades());
		}

		model.addAttribute("ingredienteForm", ingredienteForm);
		model.addAttribute("tipos", List.of("seco", "liquido", "unitario"));
		model.addAttribute("isNew", false);
		
		return "ingredientes/formulario";
    }

	@PostMapping("/{id}/editar")
	public String editar(@Valid @ModelAttribute IngredienteForm ingredienteForm,  BindingResult result, Model model, @PathVariable Long id){ 
		Ingrediente ingrediente = ingredienteService.obterPorId(id);
		if (ingrediente == null) {
            // Tratar caso o ingrediente não seja encontrado
            return "redirect:/ingredientes";
        }

		if(result.hasErrors()){
			model.addAttribute("ingredienteForm", ingredienteForm);
			model.addAttribute("tipos", List.of("seco", "liquido", "unitario"));
			model.addAttribute("isNew", false);
			
			return "ingredientes/formulario";
		}

		return this.salvar(ingredienteForm);
		
	}

    @GetMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id) {
        ingredienteService.excluir(id);
        return "redirect:/ingredientes";
    }
}
