package bank.service.TransactionService.controller;


import bank.service.TransactionService.model.BankAccount;
import bank.service.TransactionService.payload.request.CreateTransactionRequest;
import bank.service.TransactionService.payload.response.Response;
import bank.service.TransactionService.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/transactionService")
public class TransactionServiceController {

    @Autowired
    TransactionService service;

    @PostMapping("/createTransaction")
    public Response<BankAccount> createNewTransaction(@RequestBody CreateTransactionRequest request) {
        try {
            return service.addNewTransaction(request);
        } catch (Exception e) {
            // Properly specify the generic type parameter
            System.out.println(e.getMessage());
            System.out.println(e.toString());
            return new Response<BankAccount>(
                    404,
                    true,
                    0 ,
                    null,
                    null);

        }
    }
}
