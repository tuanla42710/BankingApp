package bank.service.TransactionService.Event.EventProcess;


import bank.service.TransactionService.Event.TransactionEvent;
import bank.service.TransactionService.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TransactionEventConsumer {

    @Autowired
    TransactionService service;

    @KafkaListener(topics = "transaction")
    public void handleTransactionRequest(TransactionEvent event){
        service.handleTransactionProcess(event);
    }
}
