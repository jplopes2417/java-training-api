package br.com.training.service.user;

import br.com.training.dto.user.UserRequestDto;
import br.com.training.dto.user.UserResponseDto;
import br.com.training.dto.user.UserUpdateDto;
import br.com.training.exception.user.UserApiRequestException;
import br.com.training.exception.user.UserNotFoundException;
import br.com.training.mapper.user.UserMapper;
import br.com.training.model.user.User;
import br.com.training.repository.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private static final String USUARIO_NAO_ENCONTRADO = "Usuário não encontrado!";
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<?> salvarUsuario(UserRequestDto userDto) {
        validarCPFExistente(userDto.getCpf());
        validarEmailExistente(userDto.getEmail());
        log.info("Salvando o usuário: " + userDto);

        User user = UserMapper.INSTANCE.toModel(userDto);

        userRepository.save(user);
        log.info("Usuário salvo no banco: " + user.getName() + " + " + user.getEmail());

        UserResponseDto responseDto = UserMapper.INSTANCE.toResponseDto(userDto);

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> deletarUsuario(Long id) {
        User user = buscarUsuarioPorId(id);
        log.info("Deletando o usuário de ID: " + id);
        userRepository.deleteById(user.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> atualizarUsuario(long id, UserUpdateDto userDto) {
        log.info("Atualizando o usuário: " + userDto);
        validarEmailExistente(userDto.getEmail());
        userDto.atualizar(id, userRepository);
        return new ResponseEntity<>( HttpStatus.OK);

    }

    @Override
    public ResponseEntity<?> buscarUsuario(String cpf) {
        log.info("Buscando o usuário pelo cpf: " + cpf);
        UserRequestDto userRequestDto = UserMapper.INSTANCE.toDto(userRepository.findByCpf(cpf));
        UserResponseDto userResponseDto = UserMapper.INSTANCE.toResponseDto(userRequestDto);

        if (userRequestDto == null) {
            log.error("CPF não encontrado na base: " + cpf);
            throw new UserNotFoundException(USUARIO_NAO_ENCONTRADO);
        }

        log.info("Usuário encontrado.");
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> buscarTodosUsuarios() {
        List<User> users = new ArrayList<>(userRepository.findAll());
        log.info("Buscando todos os usuários da base.");

        if (users.isEmpty()) {
            log.error("Não encontrou usuário na base.");
            throw new UserNotFoundException(USUARIO_NAO_ENCONTRADO);
        }

        List<UserRequestDto> userRequestDtoList = new ArrayList<>();

        users.forEach((usuario) -> {
            userRequestDtoList.add(UserMapper.INSTANCE.toDto(usuario));
        });

        log.info("Retornando todos os usuário encontrados.");
        return new ResponseEntity<>(userRequestDtoList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> buscarTodosUsuariosPorPagina(Pageable paginacao) {
        Page<User> topicos = userRepository.findAll(paginacao);

        if (topicos.isEmpty()) {
            throw new UserNotFoundException(USUARIO_NAO_ENCONTRADO);
        }

        return new ResponseEntity<>(UserResponseDto.converterPage(topicos), HttpStatus.OK);
    }

    private void validarCPFExistente(String cpf) {
        log.info("Validando se já existe usuário com o cpf: " + cpf);

        if (userRepository.findByCpf(cpf) != null) {
            log.error("Usuário já existe e irá lançar a exception!");
            throw new UserApiRequestException("Usuário já cadastrado!");
        }
    }

    private void validarEmailExistente(String email) {
        log.info("Validando se já existe usuário com o email: " + email);

        if (userRepository.findByEmail(email) != null) {
            log.error("Usuário já existe e irá lançar a exception!");
            throw new UserApiRequestException("Email já cadastrado!");
        }
    }

    private User buscarUsuarioPorId(Long id) {
        log.info("Buscando o usuário pelo ID: " + id);
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(USUARIO_NAO_ENCONTRADO));
    }

}
