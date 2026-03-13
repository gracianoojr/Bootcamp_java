package app.controller;

import app.dto.UsuarioRequestDTO;
import app.dto.UsuarioResponseDTO;
import app.service.UsuarioService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UsuarioController.class)
class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService service;

    @Test
    @DisplayName("Deve cadastrar usuário com sucesso")
    void deveCadastrarUsuario() throws Exception {
        UsuarioResponseDTO response = new UsuarioResponseDTO(
                1L,
                "João",
                "joao@email.com",
                "11999999999"
        );

        when(service.cadastrar(any(UsuarioRequestDTO.class))).thenReturn(response);

        String json = """
                {
                  "nome": "João",
                  "email": "joao@email.com",
                  "numero": "11999999999",
                  "senha": "Senha@123"
                }
                """;

        mockMvc.perform(post("/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("João"))
                .andExpect(jsonPath("$.email").value("joao@email.com"))
                .andExpect(jsonPath("$.numero").value("11999999999"));
    }

    @Test
    @DisplayName("Deve listar usuários")
    void deveListarUsuarios() throws Exception {
        List<UsuarioResponseDTO> lista = List.of(
                new UsuarioResponseDTO(1L, "João", "joao@email.com", "11999999999"),
                new UsuarioResponseDTO(2L, "Maria", "maria@email.com", "11888888888")
        );

        when(service.listar()).thenReturn(lista);

        mockMvc.perform(get("/usuarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nome").value("João"))
                .andExpect(jsonPath("$[0].email").value("joao@email.com"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].nome").value("Maria"))
                .andExpect(jsonPath("$[1].email").value("maria@email.com"));
    }

    @Test
    @DisplayName("Deve buscar usuário por id")
    void deveBuscarUsuarioPorId() throws Exception {
        UsuarioResponseDTO response = new UsuarioResponseDTO(
                1L,
                "João",
                "joao@email.com",
                "11999999999"
        );

        when(service.buscarPorId(1L)).thenReturn(response);

        mockMvc.perform(get("/usuarios/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("João"))
                .andExpect(jsonPath("$.email").value("joao@email.com"))
                .andExpect(jsonPath("$.numero").value("11999999999"));
    }

    @Test
    @DisplayName("Deve retornar erro quando usuário não existir")
    void deveRetornarErroQuandoUsuarioNaoExistir() throws Exception {
        when(service.buscarPorId(999L))
                .thenThrow(new RuntimeException("Usuário não encontrado"));

        mockMvc.perform(get("/usuarios/999"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.erro").value("Usuário não encontrado"));
    }

    @Test
    @DisplayName("Deve atualizar usuário com sucesso")
    void deveAtualizarUsuario() throws Exception {
        UsuarioResponseDTO response = new UsuarioResponseDTO(
                1L,
                "João Atualizado",
                "joao@email.com",
                "11999999999"
        );

        when(service.atualizar(eq(1L), any(UsuarioRequestDTO.class))).thenReturn(response);

        String json = """
                {
                  "nome": "João Atualizado",
                  "email": "joao@email.com",
                  "numero": "11999999999",
                  "senha": "Senha@123"
                }
                """;

        mockMvc.perform(put("/usuarios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("João Atualizado"))
                .andExpect(jsonPath("$.email").value("joao@email.com"))
                .andExpect(jsonPath("$.numero").value("11999999999"));
    }

    @Test
    @DisplayName("Deve retornar erro ao atualizar usuário inexistente")
    void deveRetornarErroAoAtualizarUsuarioInexistente() throws Exception {
        when(service.atualizar(eq(999L), any(UsuarioRequestDTO.class)))
                .thenThrow(new RuntimeException("Usuário não encontrado"));

        String json = """
                {
                  "nome": "João Atualizado",
                  "email": "joao@email.com",
                  "numero": "11999999999",
                  "senha": "Senha@123"
                }
                """;

        mockMvc.perform(put("/usuarios/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.erro").value("Usuário não encontrado"));
    }

    @Test
    @DisplayName("Deve deletar usuário com sucesso")
    void deveDeletarUsuario() throws Exception {
        doNothing().when(service).deletar(1L);

        mockMvc.perform(delete("/usuarios/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Usuário deletado com sucesso!"));
    }

    @Test
    @DisplayName("Deve retornar erro ao deletar usuário inexistente")
    void deveRetornarErroAoDeletarUsuarioInexistente() throws Exception {
        org.mockito.Mockito.doThrow(new RuntimeException("Usuário não encontrado"))
                .when(service).deletar(999L);

        mockMvc.perform(delete("/usuarios/999"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.erro").value("Usuário não encontrado"));
    }

    @Test
    @DisplayName("Deve retornar erro quando email for duplicado")
    void deveRetornarErroQuandoEmailDuplicado() throws Exception {
        when(service.cadastrar(any(UsuarioRequestDTO.class)))
                .thenThrow(new RuntimeException("Email já cadastrado"));

        String json = """
                {
                  "nome": "João",
                  "email": "joao@email.com",
                  "numero": "11999999999",
                  "senha": "Senha@123"
                }
                """;

        mockMvc.perform(post("/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.erro").value("Email já cadastrado"));
    }

    @Test
    @DisplayName("Deve retornar erro quando nome for inválido")
    void deveRetornarErroQuandoNomeForInvalido() throws Exception {
        String json = """
                {
                  "nome": " ",
                  "email": "joao@email.com",
                  "numero": "11999999999",
                  "senha": "Senha@123"
                }
                """;

        mockMvc.perform(post("/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.erro").value("Dados inválidos"))
                .andExpect(jsonPath("$.campos.nome").value("Nome é obrigatório"));
    }

    @Test
    @DisplayName("Deve retornar erro quando email for inválido")
    void deveRetornarErroQuandoEmailForInvalido() throws Exception {
        String json = """
                {
                  "nome": "João",
                  "email": "email-invalido",
                  "numero": "11999999999",
                  "senha": "Senha@123"
                }
                """;

        mockMvc.perform(post("/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.erro").value("Dados inválidos"))
                .andExpect(jsonPath("$.campos.email").value("Email inválido"));
    }

    @Test
    @DisplayName("Deve retornar erro quando número for inválido")
    void deveRetornarErroQuandoNumeroForInvalido() throws Exception {
        when(service.cadastrar(any(UsuarioRequestDTO.class)))
                .thenThrow(new RuntimeException("Número deve conter apenas dígitos"));

        String json = """
                {
                  "nome": "João",
                  "email": "joao@email.com",
                  "numero": "11abc999",
                  "senha": "Senha@123"
                }
                """;

        mockMvc.perform(post("/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.erro").value("Número deve conter apenas dígitos"));
    }

    @Test
    @DisplayName("Deve retornar erro quando senha for fraca")
    void deveRetornarErroQuandoSenhaForFraca() throws Exception {
        when(service.cadastrar(any(UsuarioRequestDTO.class)))
                .thenThrow(new RuntimeException("Senha deve conter pelo menos uma letra maiúscula"));

        String json = """
                {
                  "nome": "João",
                  "email": "joao@email.com",
                  "numero": "11999999999",
                  "senha": "senha@123"
                }
                """;

        mockMvc.perform(post("/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.erro").value("Senha deve conter pelo menos uma letra maiúscula"));
    }
}
