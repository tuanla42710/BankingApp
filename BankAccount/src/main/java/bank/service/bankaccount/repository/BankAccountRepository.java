package bank.service.bankaccount.repository;

import bank.service.bankaccount.model.BankAccount;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Repository
@Transactional
public class BankAccountRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<BankAccount> findAllAccount(String customerId){
        String sql = """
                SELECT *
                FROM bank_account
                WHERE customer_id = ?""";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new BankAccount(
                                        rs.getString("account_number"),
                                        rs.getString("customer_id"),
                                        rs.getString("account_type"),
                                        rs.getString("account_status"),
                                        rs.getDouble("balance"),
                                        rs.getString("last_update")),
                customerId);
    }

    public int createNewAccount(String customerId, String accountType){
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = date.format(formatter);
        String accountNumber = String.valueOf((int) (Math.random()*10000000));
        while (findAccount(accountNumber).size() > 1){
            accountNumber = String.valueOf((int) (Math.random()*10000000));
        }
        String sql = """
                INSERT INTO bank_account
                VALUES (?,?,?,?,?,?)
                """;
        return jdbcTemplate.update(sql, accountNumber,
                                        customerId,
                                        accountType,
                                        "inactive",
                                        0.0,
                                        formattedDate);
    }

    public List<BankAccount> findAccount(String accountNumber){
        String sql = """
                SELECT *
                FROM bank_account
                WHERE account_number = ?
                """;
        return jdbcTemplate.query(sql, (rs, rowNum)  -> new BankAccount(
                rs.getString("account_number"),
                rs.getString("customer_id"),
                rs.getString("account_type"),
                rs.getString("account_status"),
                rs.getDouble("balance"),
                rs.getString("last_update")),
                accountNumber
        );
    }

    public String activeAccount(String accountNumber){
        String sql = """
                UPDATE bank_account
                SET account_status = 'active'
                WHERE account_number = ?""";
        jdbcTemplate.update(sql, accountNumber );
        return "success";
    }

}
