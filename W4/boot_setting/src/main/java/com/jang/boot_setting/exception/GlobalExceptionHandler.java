package com.jang.boot_setting.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.validation.BindException;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
	    Map<String, Object> error = new HashMap<>();
	    error.put("timestamp", LocalDateTime.now());
	    error.put("message", "MethodArgumentNotValidException");

	    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = BindException.class)
	public ResponseEntity<Object> handleBindException(BindException e){
	    Map<String, Object> error = new HashMap<>();
	    error.put("returnCode", 400);
	    error.put("returnMessage", "데이터 형식이 잘못되었습니다.");

	    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = NullPointerException.class)
	public ResponseEntity<Object> handleNullPointerException(NullPointerException e){
	    Map<String, Object> error = new HashMap<>();
	    error.put("timestamp", LocalDateTime.now());
	    error.put("message", "NullPointerException");

	    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

}
