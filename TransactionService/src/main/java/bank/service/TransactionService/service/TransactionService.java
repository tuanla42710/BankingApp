package bank.service.TransactionService.service;

import bank.service.TransactionService.APIConnection.HttpApiCall;
import bank.service.TransactionService.repository.TransactionRepository;
import bank.service.event.TransactionEvent;
import bank.service.event.TransactionStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import bank.service.TransactionService.model.BankAccount;
import bank.service.TransactionService.payload.request.CreateTransactionRequest;
import bank.service.TransactionService.payload.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
//@RequiredArgsConstructor
//@Transactional
public class TransactionService {


    @Autowired
    KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    TransactionRepository repository;

    public Response<BankAccount> addNewTransaction(CreateTransactionRequest request){
        String baseUrl = "http://localhost:8080/api/bankingAccount/getAccountInfo";
        String url = UriComponentsBuilder.fromHttpUrl(baseUrl)
                                         .queryParam("accountId",request.getTransactionData().getAccountId()).toUriString();
        HttpApiCall apiCall = new HttpApiCall(new RestTemplate());
        Response<BankAccount> account =  apiCall.fetchResultFromGet(url, "admin", "admin", BankAccount.class);
        if (account.getData().isEmpty() ){
            return new Response<BankAccount>(200,true, 0 ,"Cid is not found",null);
        }
        ObjectMapper mapper = new ObjectMapper();
        BankAccount bankAccount = mapper.convertValue(
                account.getData().get(0),
                BankAccount.class
        );
        if (!bankAccount.getAccountStatus().equals("active") ){
            return new Response<BankAccount>(200,
                    true,
                    0 ,
                    "Cid is not active, please activate before execute transaction",
                    null);
        }
        double transAmount = request.getTransactionData().getAmount();


        if (transAmount > bankAccount.getBalance()){
            return new Response<BankAccount>(
                    200,
                    true,
                    0 ,
                    "Balance is not enough to execute transaction",
                    null);
        }

        TransactionEvent event = new TransactionEvent(request.getTransactionData().getAccountId(),
                                                      request.getTransactionData().getCustomerId(),
                                                      request.getTransactionData().getAmount(),
                                                      request.getTransactionData().getContent(),
                                                      request.getTransactionData().getIct(),
                                                      request.getTransactionData().getOfsAccount(),
                                                      request.getTransactionData().getOfsCustomer(),
                                                      request.getTransactionData().getCategory(),
                                                      TransactionStatus.PENDING);

        kafkaTemplate.send("bankTransaction",event);


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
