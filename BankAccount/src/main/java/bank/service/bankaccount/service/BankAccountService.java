package bank.service.bankaccount.service;

import bank.service.bankaccount.model.BankAccount;
import bank.service.bankaccount.model.User;
import bank.service.bankaccount.payload.request.CreateAccountRequest;
import bank.service.bankaccount.repository.BankAccountRepository;
import bank.service.bankaccount.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BankAccountService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BankAccountRepository bankAccountRepository;

    public Optional<User> getUser(int id){
        return userRepository.findById(id);
    }

    public void addNewUser(User user){
        userRepository.save(user);
    }

    public List<BankAccount> getAllBankingAccount(String customerId){
        return bankAccountRepository.findAllAccount(customerId);
    }

    public void createNewAccount(CreateAccountRequest request){
        bankAccountRepository.createNewAccount(request.getCustomerId(), request.getAccountType());
        return;
    }

    public String activeAccount(String accountId){
        bankAccountRepository.activeAccount(accountId);
        return "success";
    }






}
