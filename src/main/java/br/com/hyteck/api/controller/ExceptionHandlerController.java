package br.com.hyteck.api.controller;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ControllerAdvice
@Hidden
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {
    private final MessageSource messageSource;

    public ExceptionHandlerController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {

        String messageUser = messageSource.getMessage("mensagem.invalida", null,
                LocaleContextHolder.getLocale());
        String messageDev = ex.getCause() != null ? ex.getCause().toString() : ex.toString();
        List<Erro> erros = Collections.singletonList(new Erro(messageDev, messageUser));

        return handleExceptionInternal(ex, erros, headers, status, request);
    }

    @ExceptionHandler({NoSuchFieldException.class})
    protected ResponseEntity<Object> handleNoSuchFieldException(NoSuchFieldException ex, WebRequest request) {
        String messageUser = messageSource.getMessage("parametro.invalido", null, LocaleContextHolder.getLocale());
        String messageDev = ex.getCause() != null ? ex.getCause().toString() : ex.toString();
        List<Erro> erros = Collections.singletonList(new Erro(messageDev, messageUser));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);

    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<Erro> erros = criarListaDeErros(ex.getBindingResult());
        return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
    }


    @ExceptionHandler({EmptyResultDataAccessException.class})
    public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request) {
        String messageUser = messageSource.getMessage("recurso.nao-encontrado", null, LocaleContextHolder.getLocale());
        String messageDev = ex.getCause() != null ? ex.getCause().toString() : ex.toString();
        List<Erro> erros = Collections.singletonList(new Erro(messageDev, messageUser));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    private List<Erro> criarListaDeErros(BindingResult bindingResult) {
        List<Erro> errors = new ArrayList<>();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            String mensagemUsuario = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            String mensagemDesenvolvedor = fieldError.toString();
            errors.add(new Erro(mensagemDesenvolvedor, mensagemUsuario));
        }

        return errors;
    }


    public class Erro {
        private String messageDeveloper;
        private String messageUser;


        Erro(String messageDeveloper, String messageUser) {
            this.messageDeveloper = messageDeveloper;
            this.messageUser = messageUser;
        }

        public String getMessageDeveloper() {
            return messageDeveloper;
        }

        public String getMessageUser() {
            return messageUser;
        }
    }

}
