package bank.service.TransactionService.service;

import bank.service.TransactionService.APIConnection.HttpApiCall;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import bank.service.TransactionService.model.BankAccount;
import bank.service.TransactionService.payload.request.CreateTransactionRequest;
import bank.service.TransactionService.payload.response.Response;
import bank.service.TransactionService.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.http.HttpHeaders;


@Service
//@RequiredArgsConstructor
public class TransactionService {

    private final WebClient.Builder webClientBuilder;

    @Autowired  // @Autowired is optional in Spring 4.3+ for single constructor
    public TransactionService(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @Autowired
    TransactionRepository repository;

    public Response<BankAccount> addNewTransaction(String accountId){
        String baseUrl = "http://localhost:8080/api/bankingAccount/getAccountInfo";
        String url = UriComponentsBuilder.fromHttpUrl(baseUrl).queryParam("accountId",accountId).toUriString();
        System.out.println(url);
        HttpApiCall apiCall = new HttpApiCall(new RestTemplate());
        System.out.println("hello");
        return apiCall.fetchResultFromGet(url, "admin", "admin");

    }
}
