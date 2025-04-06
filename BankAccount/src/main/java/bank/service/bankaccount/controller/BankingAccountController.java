package bank.service.bankaccount.controller;


import bank.service.bankaccount.model.BankAccount;
import bank.service.bankaccount.payload.request.CreateAccountRequest;
import bank.service.bankaccount.payload.response.Response;
import bank.service.bankaccount.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/api/bankingAccount",produces = MediaType.APPLICATION_JSON_VALUE)
public class BankingAccountController {

    @Autowired
    BankAccountService service;

    @GetMapping("/getAllAcount")
    public Response<BankAccount> getAllAccount(@RequestParam(name = "customerId") String customerId){
        try {
            List<BankAccount> bankAccounts = service.getAllBankingAccount(customerId);
            return new Response<>(200, Boolean.FALSE, 0, null, new ArrayList<>(bankAccounts));
        } catch (Exception e){
            return new Response<>(404,Boolean.TRUE, e.hashCode(), e.getMessage(), null);
        }
    }

    @PostMapping("/createNewAccount")
    public Response<Objects> createNewAccount(@RequestBody CreateAccountRequest request){
        try {
            service.createNewAccount(request);
            return new Response<>(200, Boolean.FALSE, 0, null, new ArrayList<>());
        } catch (Exception e){
            return new Response<>(404,Boolean.TRUE, e.hashCode(), e.getMessage(), null);
        }
    }

    @GetMapping("/activeAccount")
    public Response<Objects> activeAccount(@RequestParam String accountId){
        try {
            service.activeAccount(accountId);
            return new Response<>(200, Boolean.FALSE, 0, null, new ArrayList<>());
        } catch (Exception e) {
            return new Response<>(404,Boolean.TRUE, e.hashCode(), e.getMessage(), null);
        }
    }

    @GetMapping(value = "/getAccountInfo",produces = { MediaType.APPLICATION_JSON_VALUE })
    public Response<BankAccount> getAccountInfo(@RequestParam(name = "accountId") String accountId){
        try {
            List<BankAccount> accounts = service.getAccountInfo(accountId);
            return new Response<>(200, Boolean.FALSE, 0, null, new ArrayList<>(accounts));
        } catch (Exception e){
            return new Response<>(404,Boolean.TRUE, e.hashCode(), e.getMessage(), null);
        }
    }

}
