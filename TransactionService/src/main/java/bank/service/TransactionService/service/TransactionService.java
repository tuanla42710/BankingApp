package bank.service.TransactionService.service;


import bank.service.TransactionService.model.BankAccount;
import bank.service.TransactionService.payload.request.CreateTransactionRequest;
import bank.service.TransactionService.payload.response.Response;
import bank.service.TransactionService.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class TransactionService {

    @Autowired
    private final WebClient.Builder webClientBuilder;

    @Autowired
    TransactionRepository repository;

    public Response addNewTransaction(CreateTransactionRequest request){
        //repository.save(request.getTransaction());
        Response<BankAccount> response = webClientBuilder.build().get()
                .uri("localhost:8080/api/bankingAccount/getAccountInfo", request.getTransaction().getAccountId())
                .retrieve()
                .bodyToMono(Response.class).block();
        return response;

    }
}
