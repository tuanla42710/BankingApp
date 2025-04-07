package bank.service.TransactionService.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BankAccount {

    private String accountNumber;

    private String customerId;

    private String accountType;

    private String accountStatus;

    private double balance;

    private String lastUpdate;

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getAccountType() {
        return accountType;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public double getBalance() {
        return balance;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }
}
