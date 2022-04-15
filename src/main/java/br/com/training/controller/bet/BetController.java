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

}
