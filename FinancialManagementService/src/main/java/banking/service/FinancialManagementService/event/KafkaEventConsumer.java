package banking.service.FinancialManagementService.event;

import bank.service.event.TransactionEvent;
import bank.service.event.TransactionStatus;
import banking.service.FinancialManagementService.service.CategoryService;
import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaEventConsumer {

    @Autowired
    CategoryService service;

    @KafkaListener(topics = "bankTransaction")
    public void processCategory(TransactionEvent event){
        if (event.getTransactionStatus() == TransactionStatus.COMPLETED && !event.getCategory().isEmpty()){
            service.processTransactionEvent(event);
        }
    }
}
