package bank.service.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class TransactionEvent {

    private String hostRef;

    private String accountId;

    private String customerId;

    private double amount;

    private String content;

    private String ict;

    private String ofsAccount;

    private String ofsCustomer;

    private String category;

    private TransactionStatus transactionStatus;
}
