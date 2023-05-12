package ru.dakon.MoexService.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.dakon.MoexService.dto.BondDTO;
import ru.dakon.MoexService.dto.StocksDTO;
import ru.dakon.MoexService.dto.TickersDTO;
import ru.dakon.MoexService.exception.LimitRequestsException;
import ru.dakon.MoexService.model.Currency;
import ru.dakon.MoexService.model.Stock;
import ru.dakon.MoexService.moexclient.CorporateBondsClient;
import ru.dakon.MoexService.moexclient.GovBondsClient;
import ru.dakon.MoexService.parser.Parser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BondService {
    private final CorporateBondsClient corporateBondsClient;
    private final GovBondsClient govBondsClient;
    private final Parser parser;

    public StocksDTO getBondsFromMoex(TickersDTO tickersDTO) {
        List<BondDTO> allBonds = new ArrayList<>();
        allBonds.addAll(getCorporateBonds());
        allBonds.addAll(getGovBonds());

        List<BondDTO> resultBonds = allBonds.stream()
                .filter(b -> tickersDTO.getTickers().contains(b.getTicker()))
                .collect(Collectors.toList());

        List<Stock> stocks = resultBonds.stream()
                .map(b -> {
                    return Stock.builder()
                            .ticker(b.getTicker())
                            .name(b.getName())
                            .figi(b.getTicker())
                            .type("Bond")
                            .currency(Currency.RUB)
                            .source("MOEX")
                            .build();
                })
                .collect(Collectors.toList());
        return new StocksDTO(stocks);
    }

    public List<BondDTO> getCorporateBonds() {
        log.info("Getting corporate bonds from Moex");
        String xmlFromMoex = corporateBondsClient.getBondsFromMoex();
        List<BondDTO> bondDTOS = parser.parse(xmlFromMoex);
        if(bondDTOS.isEmpty()) {
            log.error("Moex isn't answering for getting corporate bonds");
            throw new LimitRequestsException("Moex isn't answering for getting corporate bonds");
        }
        return bondDTOS;
    }
    public List<BondDTO> getGovBonds() {
        log.info("Getting government bonds from Moex");
        String xmlFromMoex = govBondsClient.getBondsFromMoex();
        List<BondDTO> bondDTOS = parser.parse(xmlFromMoex);
        if(bondDTOS.isEmpty()) {
            log.error("Moex isn't answering for getting goverement bonds");
            throw new LimitRequestsException("Moex isn't answering for getting goverement bonds");
        }
        return bondDTOS;
    }
}
