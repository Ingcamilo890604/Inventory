package com.gestioninventarios.inventarios.exception;

import com.gestioninventarios.inventarios.model.ApiError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CannotDeleteProductException.class)
    public ResponseEntity<Object> handleCannotDeleteProductException(CannotDeleteProductException ex){
        ApiError apiError = new ApiError();
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<Object> handleCategoryNotFoundException(CategoryNotFoundException ex){
        ApiError apiError = new ApiError();
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(InsufficientQuantityException.class)
    public ResponseEntity<Object> handleInsufficientQuantityException(InsufficientQuantityException ex){
        ApiError apiError = new ApiError();
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(InsufficientReservedQuantityException.class)
    public ResponseEntity<Object> handleInsufficientReservedQuantityException(InsufficientReservedQuantityException ex){
        ApiError apiError = new ApiError();
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Object> handleProductNotFoundException(ProductNotFoundException ex){
        ApiError apiError = new ApiError();
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(ProductsNotFoundInRangeException.class)
    public ResponseEntity<Object> handleProductsNotFoundInRangeException(ProductsNotFoundInRangeException ex){
        ApiError apiError = new ApiError();
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(SufficientProductQuantityException.class)
    public ResponseEntity<Object> handleSufficientProductQuantityException(SufficientProductQuantityException ex){
        ApiError apiError = new ApiError();
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
