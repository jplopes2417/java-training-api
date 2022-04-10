package br.com.training.controller;

import br.com.training.dto.UserRequestDto;
import br.com.training.dto.UserResponseDto;
import br.com.training.mapper.UserMapper;
import br.com.training.repository.UserRepository;
import br.com.training.service.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(UserController.class)
@ExtendWith(SpringExtension.class)
class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @MockBean
    private UserServiceImpl userService;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void criarUsuarioComSucessoViaController() throws Exception {
        URI uri = new URI("/users");

        UserRequestDto userRequestDto = userRequestDtoValido();
        UserResponseDto userResponseDto = UserMapper.INSTANCE.toResponseDto(userRequestDto);

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("name", userRequestDto.getName());
        jsonObject.put("cpf", userRequestDto.getCpf());
        jsonObject.put("birthDate", LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        jsonObject.put("email", userRequestDto.getEmail());

        ResponseEntity responseEntity = new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);

        // POR ALGUM MOTIVO ESTÁ DANDO ERRO NESSE CARA
        String corpoEnvio = jsonObject.toString();
        // COM OBJECTMAPPER TAMBÉM TÁ DANDO ERRO
        System.out.println(objectMapper.writeValueAsString(userRequestDto));

        when(userService.salvarUsuario(any(UserRequestDto.class))).thenReturn(responseEntity);

        String teste = "{\n" +
                "        \"name\": \"Teste User\",\n" +
                "        \"email\": \"teste@teste.algo2\",\n" +
                "        \"cpf\": \"172.118.470-85\",\n" +
                "        \"birthDate\": \"2021-08-08\"\n" +
                "}\n";

        mockMvc.perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(teste)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void buscarUsuarioComCPFValido() throws Exception {

        String urlBuscarPorCPF = "/users/{cpf}";

        UserRequestDto userRequestDto = userRequestDtoValido();
        UserResponseDto userResponseDto = UserMapper.INSTANCE.toResponseDto(userRequestDto);

        ResponseEntity response = new ResponseEntity<>(userResponseDto, HttpStatus.OK);

        when(userService.buscarUsuario(any())).thenReturn(response);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .get(urlBuscarPorCPF, userRequestDto.getCpf()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String name = JsonPath.read(mvcResult.getResponse().getContentAsString(), "$.name");
        String email = JsonPath.read(mvcResult.getResponse().getContentAsString(), "$.email");
        String birthDate = JsonPath.read(mvcResult.getResponse().getContentAsString(), "$.birthDate");

        UserResponseDto userResponseRetorno = UserResponseDto.builder().name(name).email(email).birthDate(LocalDate.parse(birthDate)).build();
        validarRetornoUserResponse(userResponseDto, userResponseRetorno);
    }

    @Test
    void deleteUser() {
    }

    @Test
    void updateUser() {
    }

    // TODO: PENSAR NUMA FORMA DE EXTRAIR ESSE MÉTODO
    private UserRequestDto userRequestDtoValido() {
        return UserRequestDto.builder()
                .name("TESTE")
                .email("teste@teste.algo")
                .cpf("680.983.050-23")
                .birthDate(LocalDate.now())
                .build();
    }

    private void validarRetornoUserResponse(UserResponseDto userResponseDto, UserResponseDto userResponseRetorno) {
        assertEquals(userResponseDto.getEmail(), userResponseRetorno.getEmail());
        assertEquals(userResponseDto.getName(), userResponseRetorno.getName());
        assertEquals(userResponseDto.getBirthDate(), userResponseRetorno.getBirthDate());
    }
}