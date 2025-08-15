package com.postion.airlineorderbackend.exception;

import com.postion.airlineorderbackend.dto.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponse<Object>> handleBusinessException(BusinessException ex) {
        log.error("业务异常: {}", ex.getMessage(), ex);
        ApiResponse<Object> apiResponse = ApiResponse.error(ex.getStatus().value(), ex.getMessage());
        return new ResponseEntity<>(apiResponse, ex.getStatus());
    }

    @ExceptionHandler(DataAccessException.class)
    public  ResponseEntity<ApiResponse<Object>> handleDbException(DataAccessException ex){
        log.error("数据库异常: {}", ex.getMessage(), ex);
        ApiResponse<Object> apiResponse = ApiResponse.error(500,"数据库异常，请联系管理员");
        return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleGlobalException(Exception ex) {
        log.error("未捕获的系统异常: {}", ex.getMessage(), ex);
        ApiResponse<Object> apiResponse = ApiResponse.error(500, "服务器内部错误，请联系管理员");
        return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
