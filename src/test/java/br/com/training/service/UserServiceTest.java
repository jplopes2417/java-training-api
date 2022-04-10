package br.com.training.service;

import br.com.training.dto.UserRequestDto;
import br.com.training.dto.UserResponseDto;
import br.com.training.dto.UserUpdateDto;
import br.com.training.exception.UserApiRequestException;
import br.com.training.exception.UserNotFoundException;
import br.com.training.mapper.UserMapper;
import br.com.training.model.User;
import br.com.training.repository.UserRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setup() {
        this.userService = new UserServiceImpl(userRepository);
    }

    @Test
    void deveriaSalvarUsuarioComSucesso() {

        UserRequestDto userRequestDto = userRequestDtoValido();
        UserResponseDto userResponseDto = UserMapper.INSTANCE.toResponseDto(userRequestDto);
        ResponseEntity<?> responseEntitySucesso = new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
        User user = UserMapper.INSTANCE.toModel(userRequestDto);

        when(userRepository.findByCpf(user.getCpf())).thenReturn(null);
        when(userRepository.findByEmail(user.getEmail())).thenReturn(null);

        ResponseEntity<?> responseEntity = userService.salvarUsuario(userRequestDto);

        verify(userRepository, times(1)).save(user);
        Assert.assertEquals(responseEntitySucesso, responseEntity);

    }

    @Test
    void naoDeveriaSalvarUsuarioComCPFRepetido() {

        UserRequestDto userRequestDto = userRequestDtoValido();
        User user = UserMapper.INSTANCE.toModel(userRequestDto);

        when(userRepository.findByCpf(user.getCpf())).thenReturn(user);
        Assert.assertThrows(UserApiRequestException.class, () -> userService.salvarUsuario(userRequestDto));

    }

    @Test
    void naoDeveriaSalvarUsuarioComEmailRepetido() {

        UserRequestDto userRequestDto = userRequestDtoValido();
        User user = UserMapper.INSTANCE.toModel(userRequestDto);

        when(userRepository.findByEmail(user.getEmail())).thenReturn(user);
        Assert.assertThrows(UserApiRequestException.class, () -> userService.salvarUsuario(userRequestDto));

    }

    @Test
    void deletarUsuarioComSucesso() {

        UserRequestDto userRequestDto = userRequestDtoValido();
        ResponseEntity<?> responseEntitySucesso = new ResponseEntity<>(null, HttpStatus.OK);
        User user = UserMapper.INSTANCE.toModel(userRequestDto);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        ResponseEntity<?> responseEntity = userService.deletarUsuario(1L);

        verify(userRepository, times(0)).deleteById(1L);
        Assert.assertEquals(responseEntitySucesso, responseEntity);

    }

    @Test
    void naoDeveriaDeletarUsuarioInexistente() {

        when(userRepository.findById(1L)).thenThrow(new UserNotFoundException("Usuário não encontrado!"));

        Assert.assertThrows(UserNotFoundException.class, () -> userService.deletarUsuario(1L));
        verify(userRepository, times(0)).deleteById(1L);

    }

    @Test
    void atualizarUsuarioComSucesso() {

        UserUpdateDto userUpdateDto = userUpdateDtoValido();
        UserRequestDto userRequestDto = userRequestDtoValido();
        User user = UserMapper.INSTANCE.toModel(userRequestDto);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.findByEmail(userUpdateDto.getEmail())).thenReturn(null);

        userService.atualizarUsuario(1L, userUpdateDto);

        Assert.assertEquals(user.getName(), userUpdateDto.getName());
        Assert.assertEquals(user.getEmail(), userUpdateDto.getEmail());

    }

    @Test
    void naoDeveriaAtualizarUsuarioComEmailJaExistenteNoBancoDeDados() {

        UserUpdateDto userUpdateDto = userUpdateDtoValido();
        UserRequestDto userRequestDto = userRequestDtoValido();
        userRequestDto.setEmail(userUpdateDto.getEmail());

        User user = UserMapper.INSTANCE.toModel(userRequestDto);

        when(userRepository.findByEmail(userUpdateDto.getEmail())).thenReturn(user);
        Assert.assertThrows(UserApiRequestException.class, () -> userService.atualizarUsuario(1L, userUpdateDto));

    }

    @Test
    void buscarUsuarioComSucesso() {
        UserRequestDto userRequestDto = userRequestDtoValido();
        User user = UserMapper.INSTANCE.toModel(userRequestDto);
        UserResponseDto userResponseDto = UserMapper.INSTANCE.toResponseDto(userRequestDto);
        ResponseEntity<?> responseEntitySucesso = new ResponseEntity<>(userResponseDto, HttpStatus.OK);

        when(userRepository.findByCpf(user.getCpf())).thenReturn(user);

        ResponseEntity<?> responseEntity = userService.buscarUsuario(userRequestDto.getCpf());
        Assert.assertEquals(responseEntitySucesso, responseEntity);
    }

    @Test
    void naoDeveriaEncontrarUsuarioComCPFInexistenteNaBase() {
        UserRequestDto userRequestDto = userRequestDtoValido();
        User user = UserMapper.INSTANCE.toModel(userRequestDto);

        when(userRepository.findByCpf(user.getCpf())).thenReturn(null);

        Assert.assertThrows(UserNotFoundException.class, () -> userService.buscarUsuario(userRequestDto.getCpf()));
    }

    @Test
    void listarTodosOsUsuariosComSucesso() {

        UserRequestDto userRequestDto = userRequestDtoValido();
        User user = UserMapper.INSTANCE.toModel(userRequestDto);
        List<User> users = new ArrayList<>();

        users.add(user);
        users.add(user);

        when(userRepository.findAll()).thenReturn(users);

        List<UserRequestDto> userRequestDtoList = new ArrayList<>();
        users.forEach(usuarioTeste -> userRequestDtoList.add(UserMapper.INSTANCE.toDto(usuarioTeste)));

        ResponseEntity<?> responseEntitySucesso = new ResponseEntity<>(userRequestDtoList, HttpStatus.OK);
        ResponseEntity<?> responseEntity = userService.buscarTodosUsuarios();

        Assert.assertEquals(responseEntitySucesso, responseEntity);


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

    // TODO: PENSAR NUMA FORMA DE EXTRAIR ESSE MÉTODO
    private UserUpdateDto userUpdateDtoValido() {
        return UserUpdateDto.builder()
                .name("UPDATE")
                .email("teste_update@teste.algo")
                .build();
    }
}