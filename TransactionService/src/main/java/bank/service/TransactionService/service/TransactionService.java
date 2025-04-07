package bank.service.TransactionService.service;

import bank.service.TransactionService.APIConnection.HttpApiCall;
import bank.service.TransactionService.Event.TransactionEvent;
import bank.service.TransactionService.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import bank.service.TransactionService.model.BankAccount;
import bank.service.TransactionService.payload.request.CreateTransactionRequest;
import bank.service.TransactionService.payload.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;


@Service
//@RequiredArgsConstructor
@Transactional
public class TransactionService {

//    private final WebClient.Builder webClientBuilder;
//
//    @Autowired  // @Autowired is optional in Spring 4.3+ for single constructor
//    public TransactionService(WebClient.Builder webClientBuilder) {
//        this.webClientBuilder = webClientBuilder;
//    }

    @Autowired
    KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    TransactionRepository repository;

    public Response<BankAccount> addNewTransaction(CreateTransactionRequest request){
        String baseUrl = "http://localhost:8080/api/bankingAccount/getAccountInfo";
        String url = UriComponentsBuilder.fromHttpUrl(baseUrl)
                                         .queryParam("accountId",request.getTransactionData().getAccountId()).toUriString();
        HttpApiCall apiCall = new HttpApiCall(new RestTemplate());
        Response<BankAccount> account =  apiCall.fetchResultFromGet(url, "admin", "admin");
        if (account.getData().isEmpty() ){
            return new Response<BankAccount>(200,true, 0 ,"Cid is not found",null);
        }
        if (!account.getData().get(0).getAccountStatus().equals("active") ){
            return new Response<BankAccount>(200,
                    true,
                    0 ,
                    "Cid is not active, please activate before execute transaction",
                    null);
        }
        double transAmount = request.getTransactionData().getAmount();

        if (transAmount < account.getData().get(0).getBalance()){
            return new Response<BankAccount>(
                    200,
                    true,
                    0 ,
                    "Balance is not enough to execute transaction",
                    null);
        }

        TransactionEvent eventDebit = new TransactionEvent(request.getTransactionData().getAccountId(),
                                                      request.getTransactionData().getCustomerId(),
                                                      request.getTransactionData().getAmount(),
                                                      request.getTransactionData().getContent(),
                                                      request.getTransactionData().getIct(),
                                                      request.getTransactionData().getOfsAccount(),
                                                      request.getTransactionData().getCategory());

        kafkaTemplate.send("transaction",eventDebit);

        TransactionEvent eventCredit = new TransactionEvent(request.getTransactionData().getOfsAccount(),
                request.getTransactionData().getCustomerId(),
                request.getTransactionData().getAmount(),
                request.getTransactionData().getContent(),
                "1",
                request.getTransactionData().getAccountId(),
                request.getTransactionData().getCategory());

        kafkaTemplate.send("transaction",eventCredit);

        return new Response<BankAccount>(200,
                false,
                0 ,
                null,
                null);
    }

    public void handleTransactionProcess(TransactionEvent event){
        repository.saveTransaction(event);
    }
}
