package br.com.training.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import br.com.training.dto.request.UserRequestDto;
import br.com.training.dto.update.UserUpdateDto;
import br.com.training.service.UserService;
import br.com.training.service.UserServiceImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RestControllerAdvice
@RequestMapping("/users")
public class UserController {

	private final UserService userService;

	public UserController(UserServiceImpl userService) {
		this.userService = userService;
	}

	@PostMapping
	public ResponseEntity<?> createUser(@Valid @RequestBody UserRequestDto user) {
		return userService.salvarUsuario(user);
	}

	@GetMapping (value = "/{cpf}")
    public ResponseEntity<?> getUser (@PathVariable String cpf){
		return userService.buscarUsuario(cpf);
    }

    @DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id){
		return userService.deletarUsuario(id);
	}

	@PutMapping(value = "/{id}")
	@Transactional
	public ResponseEntity<?> updateUser(@PathVariable long id, @Valid @RequestBody UserUpdateDto userUpdateDto){
		return userService.atualizarUsuario(id, userUpdateDto);
	}

	@GetMapping
	public ResponseEntity<?> getUsers(){
		return userService.buscarTodosUsuarios();
	}

	@GetMapping(value = "/page")
	public ResponseEntity<?> getUsersByPage(@PageableDefault(sort = "id", direction = Sort.Direction.DESC, page = 0, size = 1) Pageable paginacao){
		return userService.buscarTodosUsuariosPorPagina(paginacao);
	}

}
