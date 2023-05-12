package ru.dakon.MoexService.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;
import ru.dakon.MoexService.model.StockPrice;

import java.util.List;
@AllArgsConstructor
@Value
public class StocksPricesDTO {
    private List<StockPrice> prices;
}
