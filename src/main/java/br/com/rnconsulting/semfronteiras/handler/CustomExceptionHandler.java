package br.com.rnconsulting.semfronteiras.handler;

import br.com.rnconsulting.semfronteiras.Exception.CustomException;
import br.com.rnconsulting.semfronteiras.dto.ErroDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CustomExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(CustomException.class)
    public ErroDTO retorno(CustomException ex){
        return new ErroDTO(ex.geterror());
    }
}
