package com.cg.cbmapp.exception;

import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cg.cbmapp.payload.ErrorResp;

import jakarta.validation.ConstraintViolationException;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	// custom exception
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<?> handleCustomException(CustomException ex) {
		ErrorResp errRes = new ErrorResp(ex.getMessage());
		errRes.setStatusCode(HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<>(errRes, HttpStatus.BAD_REQUEST);
	}

	// constraint violation handler
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<?> handleConstraintViolation(ConstraintViolationException ex) {
		ErrorResp errRes = new ErrorResp();
		errRes.setTimeStamp(Timestamp.valueOf(LocalDateTime.now()));
		errRes.setStatusCode(HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<>(errRes, HttpStatus.BAD_REQUEST);
	}

	// method argument not valid
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		List<String> validationErrs = new ArrayList<>();
		for (FieldError err : ex.getBindingResult().getFieldErrors())
			validationErrs.add(err.getDefaultMessage());

		ErrorResp errResp = new ErrorResp();
		errResp.setTimeStamp(Timestamp.valueOf(LocalDateTime.now()));
		errResp.setMessage(ex.getBindingResult().getFieldError().getDefaultMessage());
		errResp.setStatusCode(HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<Object>(errResp, status);
	}
	//@Override
	

	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<?> handleConstraintViolationException(SQLIntegrityConstraintViolationException ex) {
		ErrorResp errorResponse = new ErrorResp();
		errorResponse.setTimeStamp(Timestamp.valueOf(LocalDateTime.now()));
		errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

}