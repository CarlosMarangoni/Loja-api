package com.gft.loja.domain.exceptionhandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.gft.loja.domain.exception.EntidadeEmUsoException;
import com.gft.loja.domain.exception.EntidadeNaoEncontradaException;

@ControllerAdvice
public class LojaExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<Object> handleEntidadeNaoEncontradaException(EntidadeNaoEncontradaException ex,
			WebRequest request) {
		Erro erro = new Erro();
		erro.setMensagem("Entidade não encontrada.");
		erro.setDataHora(LocalDateTime.now());
		erro.setStatus(HttpStatus.NOT_FOUND.value());

		return handleExceptionInternal(ex, erro, new HttpHeaders(), HttpStatus.NOT_FOUND, request);

	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<Erro.Campo> campos = new ArrayList<>();

		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			String nome = ((FieldError) error).getField();
			String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			campos.add(new Erro.Campo(nome, mensagem));
		}

		Erro erro = new Erro();
		erro.setStatus(status.value());
		erro.setDataHora(LocalDateTime.now());
		erro.setMensagem("Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente");
		erro.setCampos(campos);

		return handleExceptionInternal(ex, erro, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<Erro.Campo> campos = new ArrayList<>();
		String campo = null;
		Throwable cause = ex.getCause();

		if (cause instanceof MismatchedInputException) {
			MismatchedInputException mie = (MismatchedInputException) cause; // Capturando duas excessões dentro de
																				// HttpMessageNotReadableException para
																				// buscar o nome do campo que ocasionou
																				// o erro
			if (mie.getPath() != null && mie.getPath().size() > 0) {
				campo = mie.getPath().get(0).getFieldName();
			}
		}

		campos.add(new Erro.Campo(campo, "Tipo de entrada inválido"));

		Erro erro = new Erro();
		erro.setDataHora(LocalDateTime.now());
		erro.setStatus(status.value());
		erro.setMensagem("Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente");
		erro.setCampos(campos);

		return handleExceptionInternal(ex, erro, headers, status, request);
	}

	@ExceptionHandler(EntidadeEmUsoException.class)
	protected ResponseEntity<Object> handleEntidadeEmUsoException(EntidadeEmUsoException ex, WebRequest request) {

		Erro erro = new Erro();
		erro.setDataHora(LocalDateTime.now());
		erro.setStatus(HttpStatus.CONFLICT.value());
		erro.setMensagem(ex.getMessage());
		return handleExceptionInternal(ex, erro, new HttpHeaders(), HttpStatus.CONFLICT, request);
	}

}
