package bank.service.TransactionService.controller;


import bank.service.TransactionService.payload.request.CreateTransactionRequest;
import bank.service.TransactionService.payload.response.Response;
import bank.service.TransactionService.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transactionService")
public class TransactionServiceController {

    @Autowired
    TransactionService service;

    @PostMapping("/createTransaction")
    public Response<Object> createNewTransaction(@RequestBody CreateTransactionRequest request){
        try {
            service.addNewTransaction(request);
            return service.addNewTransaction(request); //new Response<>(200,Boolean.FALSE, 0, null, null);
        } catch (Exception e){
            return new Response<>(404,Boolean.TRUE, e.hashCode(), e.getMessage(), null);
        }

    }
}
