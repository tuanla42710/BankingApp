package banking.service.FinancialManagementService.model;


import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.CustomLog;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CategorizedTransaction {

    @Id
    @Column(name = "pid")
    private int pid;

    @Column(name = "trans_id")
    private int transactionId;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "customer_id")
    private String customerId;

    @Column(name = "category")
    private String category;

    @Column(name = "amount")
    private double amount;

    @Column(name = "version")
    private int version;

    @Column(name = "exec_date")
    private String executionDate;
}
