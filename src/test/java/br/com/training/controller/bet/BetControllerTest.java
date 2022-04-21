package br.com.training.controller.bet;

import br.com.training.dto.bet.BetResponseDto;
import br.com.training.repository.bet.BetPerUserRepository;
import br.com.training.repository.bet.BetRepository;
import br.com.training.service.bet.BetServiceImpl;
import br.com.training.service.user.UserServiceImpl;
import br.com.training.utils.BetUtils;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
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

import javax.persistence.ManyToMany;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(BetController.class)
@ExtendWith(SpringExtension.class)
class BetControllerTest {

    @InjectMocks
    private BetController betController;

    @MockBean
    private BetServiceImpl betService;

    @MockBean
    private BetRepository betRepository;

    @MockBean
    private BetPerUserRepository betPerUserRepository;

    @MockBean
    private UserServiceImpl userService;

    @Autowired
    private MockMvc mockMvc;

    private URI uri;

    @BeforeEach
    public void setup() throws URISyntaxException {
        uri = new URI("/bets");
    }

    @Test
    void criarApostaComSucesso() throws Exception {
        ResponseEntity responseEntity = new ResponseEntity(HttpStatus.CREATED);
        Mockito.when(betService.salvarAposta(Mockito.any())).thenReturn(responseEntity);

        mockMvc.perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(getBetAsString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void deletarAposta() throws Exception{
        URI uri = new URI("/bets/1");
        ResponseEntity responseEntity = new ResponseEntity<>(null, HttpStatus.OK);
        Mockito.when(betService.deletarAposta(Mockito.any())).thenReturn(responseEntity);
        mockMvc.perform(MockMvcRequestBuilders.delete(uri))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    void procurarAposta() throws Exception{
        String urlBuscarPorAposta = "/bets/{id}";
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);

        BetResponseDto betResponseDto = BetResponseDto.
                builder().
                cpf("536.778.450-09").
                numbers(numbers).
                createdAt(LocalDateTime.now()).
                build();

        ResponseEntity response = new ResponseEntity<>(betResponseDto, HttpStatus.OK);
        Mockito.when(betService.buscarAposta(Mockito.any())).thenReturn(response);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .get(urlBuscarPorAposta, 1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.cpf").value(betResponseDto.getCpf()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.numbers").value(betResponseDto.getNumbers()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.createdAt").value(betResponseDto.getCreatedAt().toString()))
                .andReturn();
    }

    private String getBetAsString() {
        return "{\n" +
                "  \"cpf\": \"536.778.450-09\",\n" +
                "  \"createdAt\": \"2022-04-18T19:50:07.166Z\",\n" +
                "  \"numbers\": [\n" +
                "    2, 1, 0, 3\n" +
                "  ]\n" +
                "}";
    }
}