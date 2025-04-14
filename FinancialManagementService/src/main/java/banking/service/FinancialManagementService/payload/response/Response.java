package banking.service.FinancialManagementService.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Response<T> {
    private int statusCode;

    private Boolean error;

    private int errorCode;

    private String errorMessage;

    private List<T> data;

}