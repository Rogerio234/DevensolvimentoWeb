package com.example.projeto.controller;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.projeto.model.Veiculo;
import com.example.projeto.service.VeiculoService;

import org.springframework.http.HttpStatus;

@Controller
@RequestMapping("/veiculos")
public class VeiculoWebController {

    private final VeiculoService veiculoService;

    public VeiculoWebController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    // Mapeia GET /veiculos → redireciona para /veiculos/listar
    @GetMapping
    public String index() {
        return "redirect:/veiculos/listar";
    }

    // 1. Página de cadastro
    @GetMapping("/cadastrar")
    public String exibirFormCadastro(Model model) {
        model.addAttribute("veiculo", new Veiculo());
        return "veiculos/form";
    }
    @PostMapping("/cadastrar")
    public String cadastrarVeiculo(
            @Valid @ModelAttribute("veiculo") Veiculo veiculo,
            BindingResult result,
            RedirectAttributes ra) {

        if (result.hasErrors()) {
            // repopula o objeto no formulário em caso de erro
            return "veiculos/form";
        }
        veiculoService.salvarVeiculo(veiculo);
        ra.addFlashAttribute("success", "Veículo cadastrado com sucesso!");
        return "redirect:/veiculos/listar";
    }
    // 2. Página de listagem
    @GetMapping("/listar")
    public String listarVeiculos(Model model) {
        model.addAttribute("lista", veiculoService.listarVeiculos());
        return "veiculos/listar";
    }
    // 3. Página de edição
    @GetMapping("/editar/{id}")
    public String exibirFormEdicao(@PathVariable Long id, Model model) {
        Veiculo veiculo = veiculoService.buscarPorId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        model.addAttribute("veiculo", veiculo);
        return "veiculos/form";
    }
    @PostMapping("/editar/{id}")
    public String editarVeiculo(
            @PathVariable Long id,
            @Valid @ModelAttribute("veiculo") Veiculo veiculo,
            BindingResult result,
            RedirectAttributes ra) {

        if (result.hasErrors()) {
            // repopula o objeto no formulário em caso de erro
            return "veiculos/form";
        }
        veiculo.setId(id);
        veiculoService.salvarVeiculo(veiculo);
        ra.addFlashAttribute("success", "Veículo editado com sucesso!");
        return "redirect:/veiculos/listar";
    }
    // 4. Detalhes e exclusão
    @GetMapping("/{id}")
    public String detalhesVeiculo(@PathVariable Long id, Model model) {
        Veiculo veiculo = veiculoService.buscarPorId(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Veículo não encontrado, id: " + id
                ));
        model.addAttribute("veiculo", veiculo);
        return "veiculos/detalhe";
    }
    @PostMapping("/{id}/excluir")
    public String excluirVeiculo(@PathVariable Long id, RedirectAttributes ra) {
        veiculoService.deletarVeiculo(id);
        ra.addFlashAttribute("success", "Veículo excluído com sucesso!");
        return "redirect:/veiculos/listar";
    }
}
// Compare this snippet from RogerioJoseDosSantosSilva_pratica3/projeto/src/main/resources/templates/veiculos/form.html:     