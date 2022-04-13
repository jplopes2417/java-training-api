package br.com.training.controller.user;

import javax.transaction.Transactional;
import javax.validation.Valid;

import br.com.training.dto.user.UserRequestDto;
import br.com.training.dto.user.UserUpdateDto;
import br.com.training.service.user.UserService;
import br.com.training.service.user.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@Api("Api usuários")
public class UserController {

	private final UserService userService;

	public UserController(UserServiceImpl userService) {
		this.userService = userService;
	}

	@PostMapping
	@ApiOperation("Criar novo usuário")
	@ApiResponses({
			@ApiResponse(code = 201, message = "Usuário cadastrado"),
			@ApiResponse(code = 400, message = "Ocorreu um erro no processamento"),
			@ApiResponse(code = 500, message = "Ocorreu um erro interno")

	})
	public ResponseEntity<?> createUser(@Valid @RequestBody UserRequestDto user) {
		return userService.salvarUsuario(user);
	}

	@GetMapping (value = "/{cpf}")
	@ApiOperation("Buscar um único usuário")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Usuário encontrado"),
			@ApiResponse(code = 400, message = "Ocorreu um erro durante o processamento"),
			@ApiResponse(code = 404, message = "Usuário não foi encontrado na base de dados"),
			@ApiResponse(code = 500, message = "Ocorreu um erro interno")

	})
    public ResponseEntity<?> getUser (@PathVariable String cpf){
		return userService.buscarUsuario(cpf);
    }

    @DeleteMapping(value = "/{id}")
	@ApiOperation("Deletar usuário")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Usuário deletado com sucesso"),
			@ApiResponse(code = 400, message = "Ocorreu um erro no processamento"),
			@ApiResponse(code = 404, message = "Usuário não foi encontrado na base de dados"),
			@ApiResponse(code = 500, message = "Ocorreu um erro interno")

	})
	public ResponseEntity<?> deleteUser(@PathVariable Long id){
		return userService.deletarUsuario(id);
	}

	@PutMapping(value = "/{id}")
	@Transactional
	@ApiOperation("Atualiza usuário")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Usuário atualizado com sucesso"),
			@ApiResponse(code = 400, message = "Ocorreu um erro no processamento"),
			@ApiResponse(code = 404, message = "Usuário não foi encontrado na base de dados"),
			@ApiResponse(code = 500, message = "Ocorreu um erro interno")

	})
	public ResponseEntity<?> updateUser(@PathVariable long id, @Valid @RequestBody UserUpdateDto userUpdateDto){
		return userService.atualizarUsuario(id, userUpdateDto);
	}

	@GetMapping
	@ApiOperation("Busca todos os usuários cadastrados na base")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Usuários encontrados"),
			@ApiResponse(code = 400, message = "Ocorreu um erro no processamento"),
			@ApiResponse(code = 404, message = "Usuário não foi encontrado na base de dados"),
			@ApiResponse(code = 500, message = "Ocorreu um erro interno")

	})
	public ResponseEntity<?> getUsers(){
		return userService.buscarTodosUsuarios();
	}

	@GetMapping(value = "/page")
	@ApiOperation("Busca usuários de forma páginada")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Usuários encontrados"),
			@ApiResponse(code = 400, message = "Ocorreu um erro no processamento"),
			@ApiResponse(code = 404, message = "Usuário não foi encontrado na base de dados"),
			@ApiResponse(code = 500, message = "Ocorreu um erro interno")

	})
	public ResponseEntity<?> getUsersByPage(@PageableDefault(sort = "id", direction = Sort.Direction.DESC, page = 0, size = 1) Pageable paginacao){
		return userService.buscarTodosUsuariosPorPagina(paginacao);
	}

}
