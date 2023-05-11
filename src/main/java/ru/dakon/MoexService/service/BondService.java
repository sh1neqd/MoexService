package ru.dakon.MoexService.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dakon.MoexService.dto.StocksDTO;
import ru.dakon.MoexService.dto.TickersDTO;
import ru.dakon.MoexService.moexclient.CorporateBondsClient;
import ru.dakon.MoexService.moexclient.GovBondsClient;

@Service
@RequiredArgsConstructor
public class BondService {
    private final CorporateBondsClient corporateBondsClient;
    private final GovBondsClient govBondsClient;
    private final Parser parser;

    public StocksDTO getBondsFromMoex(TickersDTO) {

    }
}
