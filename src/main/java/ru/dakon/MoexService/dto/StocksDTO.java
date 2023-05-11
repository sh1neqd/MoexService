package ru.dakon.MoexService.dto;

import lombok.Value;
import ru.dakon.MoexService.model.Stock;

import java.util.List;

@Value
public class StocksDTO {
    List<Stock> stocks;
}
