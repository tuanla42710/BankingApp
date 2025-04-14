package bank.service.TransactionService.repository;



import bank.service.event.TransactionEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Repository
public class TransactionRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void saveTransaction(TransactionEvent event){
        String sql = """
                INSERT INTO bank_transaction (hostref ,account_number, customer_id, amount, trx_context, ict, ofs_account, ofs_customer, category, last_update)
                VALUES
                (?, ? , ?, ?, ?, ?, ?, ?, ?, ?)""";
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = date.format(formatter);

        jdbcTemplate.update(sql,
                event.getHostRef(),
                event.getAccountId(),
                event.getCustomerId(),
                event.getAmount(),
                event.getContent(),
                event.getIct(),
                event.getOfsAccount(),
                event.getOfsCustomer(),
                event.getCategory(),
                formattedDate);

        jdbcTemplate.update(sql,
                event.getHostRef(),
                event.getOfsAccount(),
                event.getOfsCustomer(),
                event.getAmount(),
                event.getContent(),
                "1",
                event.getAccountId(),
                event.getCustomerId(),
                event.getCategory(),
                formattedDate);
    }
}
