package ru.dakon.MoexService.exception;

public class BondNotFoundException extends RuntimeException{
    public BondNotFoundException(String m) {
        super(m);
    }
}
