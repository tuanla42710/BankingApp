package banking.service.FinancialManagementService.service;

import bank.service.event.TransactionEvent;
import banking.service.FinancialManagementService.model.CategorizedTransaction;
import banking.service.FinancialManagementService.model.StatisticOverview;
import banking.service.FinancialManagementService.model.Transaction;
import banking.service.FinancialManagementService.payload.request.OverviewRequest;
import banking.service.FinancialManagementService.query.OverviewQuery;
import banking.service.FinancialManagementService.repository.CategorizedTransactionRepository;
import banking.service.FinancialManagementService.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategorizedTransactionRepository repository;

    @Autowired
    TransactionRepository transactionRepository;

    public void processTransactionEvent(TransactionEvent event){
        List<Transaction> transactions = transactionRepository.getTransactionByHostRef(
                event.getHostRef(), event.getAccountId()
        );
        CategorizedTransaction categorizedTransaction = new CategorizedTransaction(
                0,
                transactions.get(0).getId(),
                transactions.get(0).getAccountId(),
                transactions.get(0).getCustomerId(),
                transactions.get(0).getCategory(),
                transactions.get(0).getAmount(),
                0,
                transactions.get(0).getLastUpdate()
        );
        repository.updateCategory(categorizedTransaction);
    }

    public void updateCategory(CategorizedTransaction transaction){
        repository.updateCategory(transaction);
    }

    public void deleteCategory(String hostRef, String accountId){
        repository.deleteCategorizedTransaction(hostRef,accountId);
    }

    public List<StatisticOverview> getStatisticOverview(OverviewRequest request){
        OverviewQuery overviewQuery = new OverviewQuery(request);
        String query = overviewQuery.buildQuery();

        return repository.getStatisticOverview(query, request);
    }


}
