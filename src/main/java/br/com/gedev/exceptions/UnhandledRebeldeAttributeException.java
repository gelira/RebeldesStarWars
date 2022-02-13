package br.com.gedev.exceptions;

public class UnhandledRebeldeAttributeException extends Exception {
    public UnhandledRebeldeAttributeException() {
        super("RebeldeAttribute não tratado");
    }
}
