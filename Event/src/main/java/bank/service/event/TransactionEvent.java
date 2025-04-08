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
    @JsonProperty("accountId")
    private String accountId;
    @JsonProperty("customerId")
    private String customerId;
    @JsonProperty("amount")
    private double amount;
    @JsonProperty("content")
    private String content;
    @JsonProperty("ict")
    private String ict;
    @JsonProperty("ofsAccount")
    private String ofsAccount;
    @JsonProperty("ofsCustomer")
    private String ofsCustomer;
    @JsonProperty("category")
    private String category;
    @JsonProperty("transactionStatus")
    private TransactionStatus transactionStatus;
}
