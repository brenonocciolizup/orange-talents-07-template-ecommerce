package br.com.zupacademy.brenonoccioli.mercadolivre.validacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ErrosDeFormHandler {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErroFormDto>> erroDeValidacaoHandler(MethodArgumentNotValidException exception){

        List<ErroFormDto> dto = new ArrayList<>();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        List<ObjectError> classErrors = exception.getBindingResult().getAllErrors();

        System.out.println( "Chegou no field Errors!" + fieldErrors.toString());

        fieldErrors.forEach(e -> {
            String msg = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            ErroFormDto erro = new ErroFormDto(e.getField(), msg);
            dto.add(erro);
        });

        //Mesmo com a anotação @ResponseStatus estava retornando status 404 em vez de 400
        //Optei por essa abordagem por ainda não conhecer um caminho melhor
        return ResponseEntity.status(400).body(dto);
    }
}