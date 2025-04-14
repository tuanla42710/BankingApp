package bank.service.TransactionService.event.EventProcess;




import bank.service.TransactionService.service.TransactionService;
import bank.service.event.TransactionEvent;
import bank.service.event.TransactionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class TransactionEventConsumer {

    @Autowired
    TransactionService service;

    @Autowired
    KafkaTemplate<String, Object> kafkaTemplate;

    @KafkaListener(topics = "bankTransaction")
    public void handleTransactionRequest(TransactionEvent event){
        if (event.getTransactionStatus() == TransactionStatus.PROCESSING){
            service.handleTransactionProcess(event);
        }
    }

    @KafkaListener(topics = "bankTransaction")
    public void handleCompleteTransaction(TransactionEvent event){
        if (event.getTransactionStatus() == TransactionStatus.COMPLETED && !event.getCategory().isEmpty()){
            kafkaTemplate.send("category", event);
        }
    }
}
