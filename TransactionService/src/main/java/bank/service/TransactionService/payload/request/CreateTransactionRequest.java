package bank.service.TransactionService.payload.request;

import bank.service.TransactionService.model.Transaction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateTransactionRequest {
    private String requestId;

    private String sessionId;

    private Transaction transaction;
}
