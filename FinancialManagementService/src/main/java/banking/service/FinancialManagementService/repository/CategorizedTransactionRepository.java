package banking.service.FinancialManagementService.repository;

import banking.service.FinancialManagementService.model.CategorizedTransaction;
import banking.service.FinancialManagementService.model.StatisticOverview;
import banking.service.FinancialManagementService.payload.request.OverviewRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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

    public void batchUpdate(List<CategorizedTransaction> categorizedTransactionList){
        String sql = """
                INSERT INTO transaction_category (trans_id, account_number, customer_id, category, amount, exec_date)
                VALUES
                (? ,? , ?, ?, ?, ?);
                """;

        List<Object[]> batchArgs = categorizedTransactionList.stream()
                .map(transaction -> new Object[]{
                        transaction.getTransactionId(),
                        transaction.getAccountNumber(),
                        transaction.getCustomerId(),
                        transaction.getCategory(),
                        transaction.getAmount(),
                        transaction.getExecutionDate()
                }).toList();

        jdbcTemplate.batchUpdate(sql, batchArgs);
    }

    public List<StatisticOverview> getStatisticOverview(String query, OverviewRequest request){
        List<StatisticOverview> statisticOverviews = new ArrayList<>();
        jdbcTemplate.query(query, (ResultSet rc ) -> {
                    String customerId = rc.getString("customer_id");
                    String type = rc.getString("income_or_expense");
                    double amount = rc.getDouble("amount");
                    if (request.getPeriod().isEmpty()){
                        statisticOverviews.add(new StatisticOverview(customerId,type,amount,null));
                    } else {
                        statisticOverviews.add(new StatisticOverview(customerId, type, amount, rc.getString("exec_date")));
                    }
                });
        return statisticOverviews;
    }


}
