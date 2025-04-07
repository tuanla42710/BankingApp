package bank.service.TransactionService.Event;


import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
public class TransactionEvent {

    private String accountId;

    private String customerId;


    private double amount;


    private String content;


    private String ict;

    private String ofsAccount;


    private String category;

    public TransactionEvent(String accountId, String customerId, double amount, String content, String ict, String ofsAccount, String category) {
        this.accountId = accountId;
        this.customerId = customerId;
        this.amount = amount;
        this.content = content;
        this.ict = ict;
        this.ofsAccount = ofsAccount;
        this.category = category;
    }

    public TransactionEvent() {

    }


    public String getAccountId() {
        return accountId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public double getAmount() {
        return amount;
    }

    public String getContent() {
        return content;
    }

    public String getIct() {
        return ict;
    }

    public String getOfsAccount() {
        return ofsAccount;
    }

    public String getCategory() {
        return category;
    }
}
