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

    @GetMapping("/createTransaction")
    public Response<BankAccount> createNewTransaction(@RequestParam(name = "accountId") String accountId) {
        try {
            return service.addNewTransaction(accountId);
        } catch (Exception e) {
            // Properly specify the generic type parameter
            System.out.println(e.getMessage());
            System.out.println(e.toString());
            e.printStackTrace();
            return new Response<BankAccount>();

        }
    }
}
