package app.controller;

import app.dto.UsuarioRequestDTO;
import app.dto.UsuarioResponseDTO;
import app.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/usuarios-view")
public class UsuarioViewController {

    private final UsuarioService usuarioService;

    public UsuarioViewController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("usuarios", usuarioService.listar());
        return "usuarios/lista";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("usuario", new UsuarioRequestDTO());
        model.addAttribute("modoEdicao", false);
        return "usuarios/form";
    }

    @PostMapping
    public String salvar(@Valid @ModelAttribute("usuario") UsuarioRequestDTO dto,
                         BindingResult result,
                         Model model,
                         RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            model.addAttribute("modoEdicao", false);
            return "usuarios/form";
        }

        try {
            usuarioService.cadastrar(dto);
            redirectAttributes.addFlashAttribute("sucesso", "Usuário cadastrado com sucesso!");
            return "redirect:/usuarios-view";
        } catch (RuntimeException e) {
            model.addAttribute("erro", e.getMessage());
            model.addAttribute("modoEdicao", false);
            return "usuarios/form";
        }
    }

    @GetMapping("/{id}")
    public String visualizar(@PathVariable Long id, Model model) {
        UsuarioResponseDTO usuario = usuarioService.buscarPorId(id);

        if (usuario == null) {
            model.addAttribute("erro", "Usuário não encontrado.");
            return "usuarios/erro";
        }

        model.addAttribute("usuario", usuario);
        return "usuarios/detalhes";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model) {
        UsuarioResponseDTO usuario = usuarioService.buscarPorId(id);

        if (usuario == null) {
            model.addAttribute("erro", "Usuário não encontrado.");
            return "usuarios/erro";
        }

        UsuarioRequestDTO dto = new UsuarioRequestDTO();
        dto.setNome(usuario.getNome());
        dto.setEmail(usuario.getEmail());
        dto.setNumero(usuario.getNumero());
        dto.setSenha("");

        model.addAttribute("usuario", dto);
        model.addAttribute("usuarioId", id);
        model.addAttribute("modoEdicao", true);

        return "usuarios/form";
    }

    @PostMapping("/{id}")
    public String atualizar(@PathVariable Long id,
                            @Valid @ModelAttribute("usuario") UsuarioRequestDTO dto,
                            BindingResult result,
                            Model model,
                            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            model.addAttribute("modoEdicao", true);
            model.addAttribute("usuarioId", id);
            return "usuarios/form";
        }

        try {
            UsuarioResponseDTO atualizado = usuarioService.atualizar(id, dto);

            if (atualizado == null) {
                model.addAttribute("erro", "Usuário não encontrado.");
                return "usuarios/erro";
            }

            redirectAttributes.addFlashAttribute("sucesso", "Usuário atualizado com sucesso!");
            return "redirect:/usuarios-view";
        } catch (RuntimeException e) {
            model.addAttribute("erro", e.getMessage());
            model.addAttribute("modoEdicao", true);
            model.addAttribute("usuarioId", id);
            return "usuarios/form";
        }
    }

    @PostMapping("/{id}/deletar")
    public String deletar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        usuarioService.deletar(id);
        redirectAttributes.addFlashAttribute("sucesso", "Usuário removido com sucesso!");
        return "redirect:/usuarios-view";
    }
}