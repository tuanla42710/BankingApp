package banking.service.FinancialManagementService.repository;

import banking.service.FinancialManagementService.model.CategorizedTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CategorizedTransactionRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void updateCategory(CategorizedTransaction transaction){
        String sql = """
                INSERT INTO transaction_category (trans_id, account_number, customer_id, category, amount, exec_date)
                VALUES
                (? ,? , ?, ?, ?, ?);
                """;
        jdbcTemplate.update(
                sql,
                transaction.getTransactionId(),
                transaction.getAccountNumber(),
                transaction.getCustomerId(),
                transaction.getCategory(),
                transaction.getAmount(),
                transaction.getExecutionDate()
        );
    }
    public void deleteCategorizedTransaction(String hostRef, String accountId){
        String sql = """
                DELETE
                FROM transaction_category
                WHERE hostRef = ? and accountId = ?
                """;
        jdbcTemplate.update(sql, hostRef,accountId);
    }

}
