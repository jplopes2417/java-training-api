package br.com.training.controller.bet;

import br.com.training.dto.bet.BetRequestDto;
import br.com.training.service.bet.BetService;
import br.com.training.service.bet.BetServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RestController
@RestControllerAdvice
@RequestMapping("/bets")
@Api("Api de apostas")
public class BetController {

    private final BetService betService;

    public BetController(BetServiceImpl betService) {
        this.betService = betService;
    }

    // TODO: Terminar de implementar os serviços

    @PostMapping
    @Transactional
    @ApiOperation("Criar nova aposta")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Aposta cadastrada"),
            @ApiResponse(code = 400, message = "Ocorreu um erro no processamento"),
            @ApiResponse(code = 500, message = "Ocorreu um erro interno")

    })
    public ResponseEntity criarAposta(@Valid @RequestBody BetRequestDto betRequestDto){
        return betService.salvarAposta(betRequestDto);
    }

    @DeleteMapping(value = "/{id}")
    @Transactional
    @ApiOperation("Deletar aposta pelo ID")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Aposta deletada"),
            @ApiResponse(code = 400, message = "Ocorreu um erro no processamento"),
            @ApiResponse(code = 404, message = "Aposta não encontrada no banco de dados"),
            @ApiResponse(code = 500, message = "Ocorreu um erro interno")

    })
    public ResponseEntity deletarAposta(@PathVariable Long id){
        return betService.deletarAposta(id);
    }

    @GetMapping(value = "/{id}")
    @Transactional
    @ApiOperation("Buscar aposta pelo ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Aposta encontrada"),
            @ApiResponse(code = 400, message = "Ocorreu um erro no processamento"),
            @ApiResponse(code = 404, message = "Aposta não encontrada no banco de dados"),
            @ApiResponse(code = 500, message = "Ocorreu um erro interno")

    })
    public ResponseEntity procurarAposta(@PathVariable Long id){
        return betService.buscarAposta(id);
    }

    @GetMapping()
    @Transactional
    @ApiOperation("Buscar todas as apostas")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Aposta encontrada"),
            @ApiResponse(code = 400, message = "Ocorreu um erro no processamento"),
            @ApiResponse(code = 404, message = "Aposta não encontrada no banco de dados"),
            @ApiResponse(code = 500, message = "Ocorreu um erro interno")

    })
    public ResponseEntity retornarTodasAsApostas(){
        return betService.buscarTodasApostas();
    }
}
