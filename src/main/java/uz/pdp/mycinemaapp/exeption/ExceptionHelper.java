package uz.pdp.mycinemaapp.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uz.pdp.mycinemaapp.common.MessageService;
import uz.pdp.mycinemaapp.payload.ApiResponse;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ExceptionHelper {

    private ObjectError error;

    @ExceptionHandler(value = {RestException.class})
    public ResponseEntity<?> exceptionHandler(RestException e) {
        return ResponseEntity
                .status(e.getStatus())
                .body(new ApiResponse(false, List.of(new ErrorData(e.getMessage(), e.getStatus().value()))));
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<?> exceptionHandler(MethodArgumentNotValidException e) {
        List<ErrorData> errorDataList = new ArrayList<>();

        List<ObjectError> allErrors = e.getAllErrors();
        for (ObjectError error : allErrors) {
            FieldError fieldError = (FieldError) error;
            ErrorData errorData = new ErrorData(error.getDefaultMessage(), HttpStatus.BAD_REQUEST.value(), fieldError.getField());
            errorDataList.add(errorData);
        }
        ApiResponse apiResponse = new ApiResponse(false, errorDataList);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<?> exceptionHandler(Exception e) {
        e.printStackTrace();
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse(MessageService.getMessage("INTERNAL_SERVER_ERROR"), false));
    }

}
