package bank.service.TransactionService.repository;


import bank.service.TransactionService.Event.TransactionEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Repository;

import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Repository
public class TransactionRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void saveTransaction(TransactionEvent event){
        String sql = """
                INSERT INTO bank_transaction (account_number, customer_id, amount, trx_context, ict, category, last_update)
                VALUES
                (?, ?, ?, ?, ?, ?)""";
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = date.format(formatter);

        jdbcTemplate.update(sql,
                event.getAccountId(),
                event.getCustomerId(),
                event.getAmount(),
                event.getContent(),
                event.getIct(),
                event.getCategory(),
                formattedDate);
    }
}
