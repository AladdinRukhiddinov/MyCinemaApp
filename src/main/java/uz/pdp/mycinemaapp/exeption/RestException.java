package uz.pdp.mycinemaapp.exeption;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.http.HttpStatus;
import uz.pdp.mycinemaapp.common.MessageService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestException extends RuntimeException {

    private String timestamp;

    private String message;

    private HttpStatus status;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    public String getTimestamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    }

    public RestException(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    public static RestException restThrow(String message, HttpStatus status) {
        return new RestException(message, status);
    }

    public static RestException notFound(String message) {
        return new RestException(message, HttpStatus.NOT_FOUND);
    }


    public static RestException forbidden() {
        return new RestException(MessageService.getMessage("FORBIDDEN"), HttpStatus.FORBIDDEN);
    }

    public static RestException badRequest() {
        return new RestException(MessageService.getMessage("BAD_REQUEST"), HttpStatus.BAD_REQUEST);
    }

    public static RestException serverError() {
        return new RestException(MessageService.getMessage("INTERNAL_SERVER_ERROR"),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
