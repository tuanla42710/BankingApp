package bank.service.TransactionService.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BankAccount {

    private String accountNumber;

    private String customerId;

    private String accountType;

    private String accountStatus;

    private double balance;

    private String lastUpdate;

}
