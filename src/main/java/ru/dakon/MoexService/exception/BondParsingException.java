package ru.dakon.MoexService.exception;

public class BondParsingException extends RuntimeException{
    public BondParsingException(Exception ex) {
        super(ex);
    }
}
