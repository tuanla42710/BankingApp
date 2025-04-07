package bank.service.TransactionService.Event;


import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


public class TransactionEvent {

    private String accountId;

    private String customerId;

    private double amount;

    private String content;

    private String ict;

    private String ofsAccount;

    private String ofsCustomer;

    private String category;

    private TransactionStatus transactionStatus;

    public TransactionEvent(String accountId, String customerId, double amount, String content, String ict, String ofsAccount, String ofsCustomer, String category, TransactionStatus transactionStatus) {
        this.accountId = accountId;
        this.customerId = customerId;
        this.amount = amount;
        this.content = content;
        this.ict = ict;
        this.ofsAccount = ofsAccount;
        this.ofsCustomer = ofsCustomer;
        this.category = category;
        this.transactionStatus = transactionStatus;
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

    public String getOfsCustomer() {
        return ofsCustomer;
    }

    public String getCategory() {
        return category;
    }

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }
}
