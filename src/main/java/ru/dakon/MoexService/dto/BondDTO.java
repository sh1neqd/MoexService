package ru.dakon.MoexService.dto;

import lombok.Value;

@Value
public class BondDTO {
    String ticker;
    String name;
    Double price;
}
