package app.controller;

import app.dto.UsuarioRequestDTO;
import app.dto.UsuarioResponseDTO;
import app.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
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

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveCadastrarUsuario() throws Exception {
        UsuarioRequestDTO request = new UsuarioRequestDTO(
                "João",
                "joao@email.com",
                "999999",
                "123456"
        );

        UsuarioResponseDTO response = new UsuarioResponseDTO(
                1L,
                "João",
                "joao@email.com",
                "999999"
        );

        when(service.cadastrar(any(UsuarioRequestDTO.class))).thenReturn(response);

        mockMvc.perform(post("/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("João"));
    }

    @Test
    void deveListarUsuarios() throws Exception {
        UsuarioResponseDTO response = new UsuarioResponseDTO(
                1L,
                "Maria",
                "maria@email.com",
                "888888"
        );

        when(service.listar()).thenReturn(List.of(response));

        mockMvc.perform(get("/usuarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Maria"));
    }

    @Test
    void deveBuscarUsuarioPorId() throws Exception {
        UsuarioResponseDTO response = new UsuarioResponseDTO(
                1L,
                "Carlos",
                "carlos@email.com",
                "777777"
        );

        when(service.buscarPorId(1L)).thenReturn(response);

        mockMvc.perform(get("/usuarios/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Carlos"));
    }
    @Test
    void deveRetornarErroQuandoEmailDuplicado() throws Exception {

        String json = """
        {
          "nome": "João",
          "email": "joao@email.com",
          "numero": "999999",
          "senha": "123456"
        }
        """;

        when(service.cadastrar(any()))
                .thenThrow(new RuntimeException("Email já cadastrado"));

        mockMvc.perform(post("/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }
    @Test
    void deveRetornarErroQuandoEmailInvalido() throws Exception {

        String json = """
        {
          "nome": "João",
          "email": "email-invalido",
          "numero": "999999",
          "senha": "123456"
        }
        """;

        mockMvc.perform(post("/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }
    @Test
    void deveRetornarErroQuandoNomeVazio() throws Exception {

        String json = """
        {
          "nome": "",
          "email": "joao@email.com",
          "numero": "999999",
          "senha": "123456"
        }
        """;

        mockMvc.perform(post("/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deveAtualizarUsuario() throws Exception {
        UsuarioRequestDTO request = new UsuarioRequestDTO(
                "Novo Nome",
                "novo@email.com",
                "123123",
                "senha"
        );

        UsuarioResponseDTO response = new UsuarioResponseDTO(
                1L,
                "Novo Nome",
                "novo@email.com",
                "123123"
        );

        when(service.atualizar(any(Long.class), any(UsuarioRequestDTO.class))).thenReturn(response);

        mockMvc.perform(put("/usuarios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Novo Nome"));
    }

    @Test
    void deveDeletarUsuario() throws Exception {
        mockMvc.perform(delete("/usuarios/1"))
                .andExpect(status().isOk());
    }
}