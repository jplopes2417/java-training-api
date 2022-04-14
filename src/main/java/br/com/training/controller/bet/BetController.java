package br.com.training.controller.bet;

import br.com.training.service.bet.BetService;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Validated
@RestController
@RestControllerAdvice
@RequestMapping("/bets")
@Api("Api de apostas")
public class BetController {

    private final BetService betService;

    public BetController(BetService betService) {
        this.betService = betService;
    }

    // TODO: Terminar de implementar os serviços


}
