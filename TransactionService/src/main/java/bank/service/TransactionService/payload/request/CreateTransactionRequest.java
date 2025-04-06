package bank.service.TransactionService.payload.request;

import bank.service.TransactionService.model.Transaction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class CreateTransactionRequest {
    private String requestId;

    private String sessionId;

    private Transaction transactionData;

    public String getRequestId() {
        return requestId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public Transaction getTransactionData() {
        return transactionData;
    }
}
