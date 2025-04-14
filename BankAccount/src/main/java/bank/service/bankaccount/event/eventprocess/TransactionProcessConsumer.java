package bank.service.bankaccount.event.eventprocess;



import bank.service.bankaccount.service.BankAccountService;
import bank.service.event.TransactionEvent;
import bank.service.event.TransactionStatus;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class TransactionProcessConsumer {

    @Autowired
    KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    BankAccountService service;

    @KafkaListener(topics = "bankTransaction",groupId = "transaction_group")
    public void processTransaction(TransactionEvent event) throws JsonProcessingException {
        if (event.getTransactionStatus() == TransactionStatus.PENDING){
            service.processTransactionEvent(event);
        }

    }
}
