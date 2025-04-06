package bank.service.TransactionService.APIConnection;

import bank.service.TransactionService.model.BankAccount;
import bank.service.TransactionService.payload.response.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;


public class HttpApiCall {


    RestTemplate restTemplate;
    public HttpApiCall(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public <T> Response<T> fetchResultFromGet(String url, String username, String password){
        ParameterizedTypeReference<Response<T>> refType = new ParameterizedTypeReference<Response<T>>() {
        };
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(username,password);
        HttpEntity<?> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<Response<T>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                httpEntity,
                refType
        );
        return response.getBody();
    }

    public static void main(String[] args){
        HttpApiCall apiCall = new HttpApiCall(new RestTemplate());
        Response<BankAccount> accountResponse = apiCall.fetchResultFromGet("http://localhost:8080/api/bankingAccount/getAccountInfo?accountId=201234521", "admin", "admin");
        System.out.println(accountResponse.getData().toString());
    }

//    @SneakyThrows
//    public <T> String requestBodyFormater(T data){
//        ObjectMapper mapper = new ObjectMapper();
//        return mapper.writeValueAsString(data);
//    }

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
