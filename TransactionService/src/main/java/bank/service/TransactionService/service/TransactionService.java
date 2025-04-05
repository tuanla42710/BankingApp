package bank.service.TransactionService.service;


import bank.service.TransactionService.payload.request.CreateTransactionRequest;
import bank.service.TransactionService.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository repository;

    public void addNewTransaction(CreateTransactionRequest request){
        repository.save(request.getTransaction());
    }
}
