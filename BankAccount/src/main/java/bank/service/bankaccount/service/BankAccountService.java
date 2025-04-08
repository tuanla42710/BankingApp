package bank.service.bankaccount.service;



import bank.service.bankaccount.model.BankAccount;
import bank.service.bankaccount.model.User;
import bank.service.bankaccount.payload.request.CreateAccountRequest;
import bank.service.bankaccount.repository.BankAccountRepository;
import bank.service.bankaccount.repository.UserRepository;
import bank.service.event.TransactionEvent;
import bank.service.event.TransactionStatus;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BankAccountService {

    @Autowired
    KafkaTemplate<String, Object> kafkaTemplate;

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

    public void activeAccount(String accountId){
        bankAccountRepository.activeAccount(accountId);
    }

    public List<BankAccount> getAccountInfo(String accountNumber){
        return bankAccountRepository.getAccountInfo(accountNumber);
    }


    public void processTransactionEvent(TransactionEvent event){
        System.out.println("hello");
        if (event.getTransactionStatus() == TransactionStatus.PENDING) {

            try {

                bankAccountRepository.updateBalance(event.getAccountId(),
                        event.getAmount(),
                        event.getIct(),
                        event.getOfsAccount());
                TransactionEvent processEvent = new TransactionEvent(
                        event.getAccountId(),
                        event.getCustomerId(),
                        event.getAmount(),
                        event.getContent(),
                        event.getIct(),
                        event.getOfsAccount(),
                        event.getOfsCustomer(),
                        event.getCategory(),
                        TransactionStatus.PROCESSING);
                kafkaTemplate.send("bankTransaction", processEvent);
            } catch (Exception e) {
                TransactionEvent failEvent = new TransactionEvent(
                        event.getAccountId(),
                        event.getCustomerId(),
                        event.getAmount(),
                        event.getContent(),
                        event.getIct(),
                        event.getOfsAccount(),
                        event.getOfsCustomer(),
                        event.getCategory(),
                        TransactionStatus.FAILED);
                kafkaTemplate.send("bankTransaction", failEvent);
            }
        }


    }






}
