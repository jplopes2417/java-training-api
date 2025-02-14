package br.com.training.controller.bet;

import br.com.training.dto.bet.BetRequestDto;
import br.com.training.dto.bet.BetResponseDto;
import br.com.training.service.bet.BetService;
import br.com.training.service.bet.BetServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

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

    @PostMapping
    @Transactional
    @ApiOperation("Criar nova aposta")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Aposta cadastrada"),
            @ApiResponse(code = 400, message = "Ocorreu um erro no processamento"),
            @ApiResponse(code = 500, message = "Ocorreu um erro interno")

    })
    public ResponseEntity<HttpStatus> criarAposta(@RequestHeader String betName, @Valid @RequestBody BetRequestDto betRequestDto){
        return betService.salvarAposta(betName, betRequestDto);
    }

    @DeleteMapping(value = "/{id}")
    @Transactional
    @ApiOperation("Deletar aposta pelo ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Aposta deletada"),
            @ApiResponse(code = 400, message = "Ocorreu um erro no processamento"),
            @ApiResponse(code = 404, message = "Aposta não encontrada no banco de dados"),
            @ApiResponse(code = 500, message = "Ocorreu um erro interno")

    })
    public ResponseEntity<HttpStatus> deletarAposta(@PathVariable Long id){
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
    public ResponseEntity<BetResponseDto> procurarAposta(@PathVariable Long id){
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
    public ResponseEntity<Set<BetResponseDto>> retornarTodasAsApostas(){
        return betService.buscarTodasApostas();
    }
}
