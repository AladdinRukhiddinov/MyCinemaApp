package uz.pdp.mycinemaapp.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.mycinemaapp.exeption.ErrorData;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse {
    private String message;
    private boolean status;
    private Object data;
    private List<ErrorData> errors;

    public ApiResponse(String message, boolean status) {
        this.message = message;
        this.status = status;
    }

    public ApiResponse(boolean success, List<ErrorData> errors) {
        this.status = success;
        this.errors = errors;
    }

    public ApiResponse(String message, boolean status, Object data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }

    public static ApiResponse successResponse(String message) {
        return new ApiResponse(message, true);
    }
}
