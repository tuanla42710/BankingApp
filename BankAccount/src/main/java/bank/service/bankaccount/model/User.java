package bank.service.bankaccount.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "bank_user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class User {
    @Id
    @Column(name = "user_id")
    private int userId;

    @Column(name = "customer_id")
    private String customerId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_pass")
    private String userPass;

    @Column(name = "last_update")
    private String lastUpdate;
}
