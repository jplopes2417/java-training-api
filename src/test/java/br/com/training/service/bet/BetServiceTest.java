package br.com.training.service.bet;

import br.com.training.dto.bet.BetRequestDto;
import br.com.training.dto.bet.BetResponseDto;
import br.com.training.exception.bet.BetAlreadyExistsException;
import br.com.training.exception.bet.BetNotFoundException;
import br.com.training.exception.user.UserNotFoundException;
import br.com.training.model.Bet;
import br.com.training.model.BetPerUser;
import br.com.training.model.User;
import br.com.training.repository.bet.BetPerUserRepository;
import br.com.training.repository.bet.BetRepository;
import br.com.training.repository.user.UserRepository;
import br.com.training.utils.BetUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class BetServiceTest {

    @Mock
    private BetRepository betRepository;

    @Mock
    private BetPerUserRepository betPerUserRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private BetServiceImpl betService;

    private BetRequestDto betRequestDto;

    private BetResponseDto betResponseDto;

    private BetPerUser betPerUser;

    private Bet bet;

    private User user;

    @BeforeEach
    public void setup(){
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);

        betRequestDto = BetRequestDto
                .builder()
                .cpf("536.778.450-09")
                .numbers(numbers)
                .createdAt(LocalDateTime.now())
                .build();

        user = User
                .builder()
                .birthDate(LocalDate.now())
                .cpf("536.778.450-09")
                .email("teste@teste.com")
                .name("teste legal")
                .id(1L)
                .build();

        // TODO: criar o bet
        bet = Bet
                .builder()
                .createdAt(betRequestDto.getCreatedAt())
                .numbers(BetUtils.formatNumbers(numbers.toString()))
                .build();

        betPerUser = BetPerUser
                .builder()
                .id(1L)
                .bet(bet)
                .user(user)
                .build();
    }

    @Test
    void deveriaSalvarAposta() {
        BetRequestDto betRequestDtoTest = betRequestDto;
        User userTest = user;
        Bet betTest = bet;

        Mockito.when(userRepository.findByCpf(betRequestDtoTest.getCpf())).thenReturn(userTest);
        Mockito.when(betRepository.getBetByNumbersString(Mockito.any(), Mockito.any())).thenReturn(new HashSet<>());
        betService.salvarAposta(betRequestDto);

        Mockito.verify(betRepository, Mockito.times(1)).save(betTest);
    }

    @Test
    void naoDeveriaSalvarApostaQuandoUsuarioJaPossuiAMesma() {
        BetRequestDto betRequestDtoTest = betRequestDto;
        User userTest = user;
        Bet betTest = bet;

        HashSet<Bet> betHashSet = new HashSet<>();
        betHashSet.add(betTest);

        Mockito.when(userRepository.findByCpf(betRequestDtoTest.getCpf())).thenReturn(userTest);
        Mockito.when(betRepository.getBetByNumbersString(Mockito.any(), Mockito.any())).thenReturn(betHashSet);

        Mockito.verify(betRepository, Mockito.times(0)).save(betTest);
        assertThrows(BetAlreadyExistsException.class, () -> betService.salvarAposta(betRequestDto));
    }

    @Test
    void naoDeveriaSalvarApostaQuandoUsuarioNaoPossuiCadastroNoSistema() {
        BetRequestDto betRequestDtoTest = betRequestDto;
        Bet betTest = bet;

        Mockito.when(userRepository.findByCpf(betRequestDtoTest.getCpf())).thenReturn(null);

        Mockito.verify(betRepository, Mockito.times(0)).save(betTest);
        assertThrows(UserNotFoundException.class, () -> betService.salvarAposta(betRequestDto));
    }

    @Test
    void deletarAposta() {
        Bet betTest = new Bet(1L, bet.getNumbers(), bet.getCreatedAt());

        Mockito.when(betRepository.findById(Mockito.any())).thenReturn(Optional.of(betTest));

        betService.deletarAposta(1L);

        Mockito.verify(betRepository, Mockito.times(1)).deleteById(1L);
        Mockito.verify(betPerUserRepository, Mockito.times(1)).deleteByBet(1L);
    }

    @Test
    void naoDeveriaDeletarApostaQuandoElaNaoExiste() {
        Mockito.when(betRepository.findById(Mockito.any())).thenThrow(new BetNotFoundException("Aposta não encontrada na base de dados!"));

        Mockito.verify(betRepository, Mockito.times(0)).deleteById(1L);
        Mockito.verify(betPerUserRepository, Mockito.times(0)).deleteByBet(1L);
        assertThrows(BetNotFoundException.class, () -> betService.deletarAposta(1L));
    }

    @Test
    void buscarAposta() {

        BetPerUser betPerUserTest = betPerUser;
        betResponseDto = new BetResponseDto(betPerUser.getUser().getCpf(),
                BetUtils.transformStringInArray(betPerUser.getBet().getNumbers()),
                betPerUser.getBet().getCreatedAt());
        ResponseEntity responseEntity = new ResponseEntity(betResponseDto, HttpStatus.OK);

        Mockito.when(betPerUserRepository.findByBet(1L)).thenReturn(Optional.ofNullable(betPerUserTest));
        ResponseEntity<?> response = betService.buscarAposta(1L);

        Mockito.verify(betPerUserRepository, Mockito.times(1)).findByBet(1L);
        assertEquals(response, responseEntity);

    }

    @Test
    void naoDeveriaBuscarApostaQuandoElaNaoExiste() {
        Mockito.when(betPerUserRepository.findByBet(1L)).thenThrow(new BetNotFoundException("Aposta não encontrada na base de dados!"));
        Mockito.verify(betPerUserRepository, Mockito.times(0)).findByBet(1L);
        assertThrows(BetNotFoundException.class, () -> betService.buscarAposta(1L));
    }

    @Test
    void buscarTodasApostas() {
    }
}