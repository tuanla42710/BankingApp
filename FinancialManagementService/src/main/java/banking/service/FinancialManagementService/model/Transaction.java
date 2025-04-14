package banking.service.FinancialManagementService.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bank_transaction")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Transaction {
    @Id
    @Column(name = "trans_id")
    private int id;

    @Column(name = "hostref")
    private String hostRef;

    @Column(name = "account_number")
    private String accountId;

    @Column(name = "customer_id")
    private String customerId;

    @Column(name = "amount")
    private double amount;

    @Column(name = "trx_context")
    private String content;

    @Column(name = "ict")
    private String ict;

    @Column(name = "category")
    private String category;

    @Column(name = "ofs_account")
    private String ofsAccount;

    @Column(name = "ofs_customer")
    private String ofsCustomer;

    @Column(name = "last_update")
    private String lastUpdate;

}
