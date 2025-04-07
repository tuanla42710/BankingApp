package bank.service.TransactionService.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
//@Getter
public class Response<T> {
    private int statusCode;

    private Boolean error;

    private int errorCode;

    private String errorMessage;

    private List<T> data;

    public Response(int statusCode, Boolean error, int errorCode, String errorMessage, List<T> data) {
        this.statusCode = statusCode;
        this.error = error;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.data = data;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public Boolean getError() {
        return error;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public List<T> getData() {
        return data;
    }
}
