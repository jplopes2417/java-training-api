package br.com.training.controller.bet;

import br.com.training.dto.bet.BetConfigurationRequestDto;
import br.com.training.dto.bet.BetConfigurationResponseDto;
import br.com.training.service.bet.BetConfigurationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RestControllerAdvice
@RequestMapping("/bets/configuration")
@Api("Api de configuração de apostas")
public class BetConfigurationController {

    private final BetConfigurationService betConfigurationService;

    public BetConfigurationController(BetConfigurationService betConfigurationService) {
        this.betConfigurationService = betConfigurationService;
    }

    @PostMapping
    @ApiOperation("Criar nova configuração")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses({
            @ApiResponse(code = 201, message = "Configuração criada."),
            @ApiResponse(code = 400, message = "Ocorreu um erro no processamento"),
            @ApiResponse(code = 500, message = "Ocorreu um erro interno")

    })
    public void criarNovaConfiguracao(@RequestBody BetConfigurationRequestDto betConfigurationRequestDto){
        betConfigurationService.salvarNovaConfiguracaoAposta(betConfigurationRequestDto);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation("Deletar configuração")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    @ApiResponses({
            @ApiResponse(code = 204, message = "Configuração deletada."),
            @ApiResponse(code = 400, message = "Ocorreu um erro no processamento"),
            @ApiResponse(code = 404, message = "Aposta não encontrada."),
            @ApiResponse(code = 500, message = "Ocorreu um erro interno")

    })
    public void deletarConfiguracao(@PathVariable String id){
        betConfigurationService.deletarConfiguracaoAposta(id);
    }

    @GetMapping(value = "/{id}")
    @Transactional
    @ApiOperation("Buscar configuração pelo ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Configuração encontrada"),
            @ApiResponse(code = 400, message = "Ocorreu um erro no processamento"),
            @ApiResponse(code = 404, message = "Aposta não encontrada no banco de dados"),
            @ApiResponse(code = 500, message = "Ocorreu um erro interno")
    })
    public BetConfigurationResponseDto buscarConfiguracaoAposta(@PathVariable String id){
        return betConfigurationService.buscarConfiguracaoAposta(id);
    }
}
