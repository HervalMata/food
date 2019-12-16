package com.herval.food.api.exceptionhandler;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import com.herval.food.core.validation.ValidacaoException;
import com.herval.food.domain.exception.EntidadeEmUsoException;
import com.herval.food.domain.exception.EntidadeNaoEncontradaException;
import com.herval.food.domain.exception.NegocioException;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
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
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/*
 * Criado Por Herval Mata em 15/12/2019
 */
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    public static final String MSG_ERRO_GENERICA_USUARIO_FINAL = "Ocorreu um erro interno inesperado no sistema. Tente novamente e se o problema persistir, entre em contato com o administrador do sistema.";

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler({ ValidacaoException.class })
    public ResponseEntity<Object> handleValidacaoException(ValidacaoException e, WebRequest request) {
        return handleValidationInternal(e, e.getBindingResult(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    private ResponseEntity<Object> handleValidationInternal(Exception e, BindingResult bindingResult, HttpHeaders headers, HttpStatus badRequest, WebRequest request) {
        ProblemType problemType = ProblemType.DADOS_INVALIDOS;
        String detail = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.";
        List<Problema.Object> problemaObjects = bindingResult.getAllErrors().stream()
                .map(objectError -> {
                    String message = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());
                    String name = objectError.getObjectName();
                    if (objectError instanceof FieldError) {
                        name = ((FieldError) objectError).getField();
                    }
                    return Problema.Object.builder()
                            .name(name)
                            .userMessage(message)
                            .build();
                })
                .collect(Collectors.toList());
        Problema problema = createProblemabuilder(HttpStatus.BAD_REQUEST, problemType, detail).userMensagem(detail).objects(problemaObjects).build();

        return handleExceptionInternal(e, problema, headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleValidationInternal(e, e.getBindingResult(), headers, status, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUncaught(Exception e, WebRequest request) {

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ProblemType problemType = ProblemType.ERRO_DE_SISTEMA;
        String detail = MSG_ERRO_GENERICA_USUARIO_FINAL;

        e.printStackTrace();
        Problema problema = createProblemabuilder(status, problemType, detail).userMensagem(detail).build();

        return handleExceptionInternal(e, problema, new HttpHeaders(), status, request);
    }


    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException e, WebRequest request, HttpHeaders headers, HttpStatus status) {

        ProblemType problemType = ProblemType.RECURSO_NAO_ENCONTRADO;
        String detail = String.format("O recurso %s, que você tentou acessar, é inexistente.", e.getRequestURL());

        Problema problema = createProblemabuilder(status, problemType, detail).userMensagem(MSG_ERRO_GENERICA_USUARIO_FINAL).build();

        return handleExceptionInternal(e, problema, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException e, HttpHeaders headers, HttpStatus status, WebRequest request) {

        if (e instanceof MethodArgumentTypeMismatchException) {
            return handleMethodArgumentTypeMismatch((MethodArgumentTypeMismatchException) e, headers, status, request);
        }

        return super.handleTypeMismatch(e, headers, status, request);
    }

    private ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ProblemType problemType = ProblemType.PARAMETRO_INVALIDO;
        String detail = String.format("O parâmetro de URL '%s' recebeu o valor '%s', "
                        + "que é de um tipo inválido. Corrija e informe um valor compatível com o tipo %s.",
                e.getName(), e.getValue(), e.getRequiredType().getSimpleName());

        Problema problema = createProblemabuilder(status, problemType, detail)
                .userMensagem(MSG_ERRO_GENERICA_USUARIO_FINAL).build();

        return handleExceptionInternal(e, problema, headers, status, request);

    }


    protected ResponseEntity<Object> handleHttpMessageNotRedeable(HttpMessageNotReadableException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Throwable rootCause = ExceptionUtils.getRootCause(e);
        if (rootCause instanceof InvalidFormatException) {
            return handleInvalidFormat((InvalidFormatException) rootCause, headers, status, request);
        } else if (rootCause instanceof PropertyBindingException) {
            return handlePropertyBinding((PropertyBindingException) rootCause, headers, status, request);
        }

        ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
        String detail = String.format("O corpo da requisição está inválido. Verifique erro de sintaxe.");

        Problema problema = createProblemabuilder(status, problemType, detail)
                .userMensagem(MSG_ERRO_GENERICA_USUARIO_FINAL).build();

        return handleExceptionInternal(e, problema, headers, status, request);

    }

    private ResponseEntity<Object> handlePropertyBinding(PropertyBindingException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String path = joinPath(e.getPath());
        ProblemType problemType = ProblemType.PARAMETRO_INVALIDO;
        String detail = String.format("A propriedade '%s' não existe. "
                + "Corrija ou remova essa propriedade e tente novamente.", path);

        Problema problema = createProblemabuilder(status, problemType, detail)
                .userMensagem(MSG_ERRO_GENERICA_USUARIO_FINAL).build();

        return handleExceptionInternal(e, problema, headers, status, request);

    }

    private String joinPath(List<JsonMappingException.Reference> references) {
        return references.stream()
                .map(ref -> ref.getFieldName())
                .collect(Collectors.joining("."));
    }

    private ResponseEntity<Object> handleInvalidFormat(InvalidFormatException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String path = joinPath(e.getPath());
        ProblemType problemType = ProblemType.PARAMETRO_INVALIDO;
        String detail = String.format("A propriedade '%s' recebeu o valor '%s', "
                        + "que é de um tipo inválido. Corrija e informe um valor compatível com o tipo %s.",
                path, e.getValue(), e.getTargetType().getSimpleName());

        Problema problema = createProblemabuilder(status, problemType, detail)
                .userMensagem(MSG_ERRO_GENERICA_USUARIO_FINAL).build();

        return handleExceptionInternal(e, problema, headers, status, request);

    }

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> tratarEntidadrNaoEncontradaException(EntidadeNaoEncontradaException e, WebRequest request) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        ProblemType problemType = ProblemType.RECURSO_NAO_ENCONTRADO;
        String detail = e.getMessage();
        Problema problema = createProblemabuilder(status, problemType, detail).build();

        return handleExceptionInternal(e, problema, new HttpHeaders(), status, request);
    }



    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<?> tratarEntidadeEmUsoException(EntidadeEmUsoException e, WebRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        ProblemType problemType = ProblemType.ENTIDADE_EM_USO;
        String detail = e.getMessage();
        Problema problema = createProblemabuilder(status, problemType, detail).build();

        return handleExceptionInternal(e, problema, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?> tratarNegocioException(NegocioException e, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ProblemType problemType = ProblemType.ERRO_NEGOCIO;
        String detail = e.getMessage();
        Problema problema = createProblemabuilder(status, problemType, detail).build();

        return handleExceptionInternal(e, problema, new HttpHeaders(), status, request);
    }

    @Override
    public ResponseEntity<Object> handleExceptionInternal(Exception e, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        if (body == null) {
            body = Problema.builder()
                    .title(status.getReasonPhrase())
                    .status(status.value()).build();
        } else if (body instanceof String) {
            body = Problema.builder()
                    .title((String) body)
                    .status(status.value()).build();
        }

        return handleExceptionInternal(e, body, headers, status, request);
    }

    private Problema.ProblemaBuilder createProblemabuilder(HttpStatus status, ProblemType problemType, String detail) {
        return Problema.builder()
                .status(status.value())
                .type(problemType.getUri())
                .title(problemType.getTitle())
                .detail(detail);
    }
}
