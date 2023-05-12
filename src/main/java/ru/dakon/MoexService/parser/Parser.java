package ru.dakon.MoexService.parser;

import ru.dakon.MoexService.dto.BondDTO;

import java.util.List;

public interface Parser {
    List<BondDTO> parse(String ratesAsString);
}
