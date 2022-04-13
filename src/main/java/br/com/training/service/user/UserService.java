package br.com.training.service.user;

import br.com.training.dto.user.UserRequestDto;
import br.com.training.dto.user.UserUpdateDto;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<?> salvarUsuario(UserRequestDto user);
    ResponseEntity<?> deletarUsuario(Long id);
    ResponseEntity<?> atualizarUsuario(long id, UserUpdateDto user);
    ResponseEntity<?> buscarUsuario(String cpf);
    ResponseEntity<?> buscarTodosUsuarios();
    ResponseEntity<?> buscarTodosUsuariosPorPagina(Pageable pagina);

}
