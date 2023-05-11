package ru.dakon.MoexService.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dakon.MoexService.dto.StocksDTO;
import ru.dakon.MoexService.dto.TickersDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bonds")
public class MoexBondController {
    private final BondService bondService;

    @PostMapping("/getBondsByTickers")
    public StocksDTO getBondsFromMoex(@RequestBody TickersDTO tickersDTO) {
        return bondService.getBondsFromMoex(tickersDTO);
    }
}
