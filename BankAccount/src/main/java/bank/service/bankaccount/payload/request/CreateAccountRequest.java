package bank.service.bankaccount.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.N;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CreateAccountRequest {
    private String requestId;
    private String sessionId;
    private String accountType;
    private String customerId;
}
