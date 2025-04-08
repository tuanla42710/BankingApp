package bank.service.TransactionService.event.EventProcess;




import bank.service.TransactionService.service.TransactionService;
import bank.service.event.TransactionEvent;
import bank.service.event.TransactionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TransactionEventConsumer {

    @Autowired
    TransactionService service;

    @KafkaListener(topics = "bankTransaction")
    public void handleTransactionRequest(TransactionEvent event){
        if (event.getTransactionStatus() == TransactionStatus.PROCESSING){
            service.handleTransactionProcess(event);
        }

    }
}
