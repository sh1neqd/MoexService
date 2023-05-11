package ru.dakon.MoexService.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class TickersDTO {
    private List<String> tickers;
}
