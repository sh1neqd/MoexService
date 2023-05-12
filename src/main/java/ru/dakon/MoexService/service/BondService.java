package ru.dakon.MoexService.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.dakon.MoexService.dto.*;
import ru.dakon.MoexService.exception.BondNotFoundException;
import ru.dakon.MoexService.exception.LimitRequestsException;
import ru.dakon.MoexService.model.Currency;
import ru.dakon.MoexService.model.Stock;
import ru.dakon.MoexService.model.StockPrice;
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
    private final BondRepository bondRepository;

    public StocksDTO getBondsFromMoex(TickersDTO tickersDTO) {
        log.info("Request for tickers {}", tickersDTO.getTickers());
        List<String> tickers = new ArrayList<>(tickersDTO.getTickers());


        List<BondDTO> allBonds = new ArrayList<>();
        allBonds.addAll(bondRepository.getGovBonds());
        allBonds.addAll(bondRepository.getCorporateBonds());
        List<BondDTO> resultBonds = allBonds.stream()
                .filter(b -> tickers.contains(b.getTicker()))
                .collect(Collectors.toList());

        List<Stock> stocks =  resultBonds.stream().map(b -> {
            return Stock.builder()
                    .ticker(b.getTicker())
                    .name(b.getName())
                    .figi(b.getTicker())
                    .type("Bond")
                    .currency(Currency.RUB)
                    .source("MOEX")
                    .build();
        }).collect(Collectors.toList());
        return new StocksDTO(stocks);
    }

    public StocksPricesDTO getPricesByFigies(FigiesDTO figiesDto) {
        log.info("Request for figies {}", figiesDto.getFigies());
        List<String> figies = new ArrayList<>(figiesDto.getFigies());
        List<BondDTO> allBonds = new ArrayList<>();
        allBonds.addAll(bondRepository.getGovBonds());
        allBonds.addAll(bondRepository.getCorporateBonds());
        figies.removeAll(allBonds.stream().map(b -> b.getTicker()).collect(Collectors.toList()));
        if(!figies.isEmpty()) {
            throw new BondNotFoundException(String.format("Bonds %s not found.", figies));
        }
        List<StockPrice> prices = allBonds.stream()
                .filter(b -> figiesDto.getFigies().contains(b.getTicker()))
                .map(b -> new StockPrice(b.getTicker(), b.getPrice() * 10))
                .collect(Collectors.toList());
        return new StocksPricesDTO(prices);
    }
}
