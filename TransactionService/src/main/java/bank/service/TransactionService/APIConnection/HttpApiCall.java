package bank.service.TransactionService.APIConnection;

import bank.service.TransactionService.model.BankAccount;
import bank.service.TransactionService.payload.response.Response;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;


public class HttpApiCall {


    RestTemplate restTemplate;
    public HttpApiCall(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public <T> Response<T> fetchResultFromGet(String url, String username, String password, Class<T> type){
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(username, password);
        HttpEntity<?> httpEntity = new HttpEntity<>(headers);

        // Use ParameterizedTypeReference to retain generic type info
        ParameterizedTypeReference<Response<T>> responseType =
                new ParameterizedTypeReference<Response<T>>() {};

        ResponseEntity<Response<T>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                httpEntity,
                responseType  // Pass the type reference directly
        );


//        System.out.println(response.getBody().getData().get(0).getClass());
//        ResponseEntity<String> rawResponse = restTemplate.exchange(
//                url,
//                HttpMethod.GET,
//                new HttpEntity<>(headers),
//                String.class
//        );
//        System.out.println(rawResponse.getBody());

        return response.getBody();
    }

    public static void main(String[] args){
        RestTemplate restTemplate = new RestTemplate();

        // Explicitly add Jackson converter to handle JSON properly
        restTemplate.getMessageConverters().add(0, new MappingJackson2HttpMessageConverter());

        HttpApiCall apiCall = new HttpApiCall(restTemplate);

        Response<BankAccount> accountResponse = apiCall.fetchResultFromGet(
                "http://localhost:8080/api/bankingAccount/getAccountInfo?accountId=201234521",
                "admin",
                "admin",
                BankAccount.class
        );

        ObjectMapper mapper = new ObjectMapper();
        BankAccount account = mapper.convertValue(
                accountResponse.getData().get(0),
                BankAccount.class
        ); // Should work now
        System.out.println(account.getAccountNumber());

    }

//    public static void main(String[] args) {
//        RestTemplate restTemplate = new RestTemplate();
//
//        // Initialize client for AccountResponse with Transaction request
//        SecureApiPostClient<List<Transaction>, TransactionRequest> client =
//                new SecureApiPostClient<>(
//                        restTemplate,
//                        "http://localhost:8080",
//                        "apiUser",
//                        "apiPass123",
//                        List.class
//                );
//
//        // Create request body
//        TransactionRequest txRequest = new TransactionRequest(
//                "ACC123456",
//                "TRANSFER",
//                500.00,
//                "ACC789012",
//                "Monthly savings"
//        );
//
//        // Make POST request
//        ApiResponse<List<Transaction>> response = client.postWithBody(
//                "/api/transactions/create",
//                txRequest
//        );
//
//
//    }
}
