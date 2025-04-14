package banking.service.FinancialManagementService.repository;

import banking.service.FinancialManagementService.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TransactionRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Transaction> getTransactionByHostRef(String hostRef, String accountId){
        String query = """
                SELECT *
                FROM bank_transaction
                WHERE hostref = ? and account_number = ?
                """;
        List<Transaction> transactions = jdbcTemplate.query(
                query,
                new Object[]{hostRef, accountId},
                new BeanPropertyRowMapper<>(Transaction.class)
        );
        return transactions;
    }


}
