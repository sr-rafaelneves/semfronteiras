package br.com.rnconsulting.semfronteiras.Exception;


public class CustomException extends RuntimeException {

    private final String error;

    public CustomException(String error) {
        this.error = error;
    }

    public String geterror() {

        return this.error;
    }
}
