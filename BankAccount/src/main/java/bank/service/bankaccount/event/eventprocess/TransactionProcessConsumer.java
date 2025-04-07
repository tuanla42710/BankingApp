package bank.service.bankaccount.event.eventprocess;


import bank.service.bankaccount.event.TransactionEvent;
import bank.service.bankaccount.service.BankAccountService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class TransactionProcessConsumer {

    @Autowired
    BankAccountService service;

    @KafkaListener(topics = "transaction",groupId = "transaction_group")
    public void processTransaction(String raw) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(raw);
        //TransactionEvent event = mapper.readValue(raw, TransactionEvent.class);
        //service.processTransactionEvent(event);
        TransactionEvent event = mapper.readValue(raw, TransactionEvent.class);
        System.out.println(event.getAccountId());
        //service.processTransactionEvent(event);
    }
}
