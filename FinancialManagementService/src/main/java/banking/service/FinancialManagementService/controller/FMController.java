package banking.service.FinancialManagementService.controller;

import banking.service.FinancialManagementService.model.CategorizedTransaction;
import banking.service.FinancialManagementService.payload.request.UpdateRequest;
import banking.service.FinancialManagementService.payload.response.Response;
import banking.service.FinancialManagementService.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/financialManagement")
public class FMController {

    @Autowired
    CategoryService service;

    @PostMapping("/updateCategory")
    public Response<Object> updateCategory(@RequestBody UpdateRequest<CategorizedTransaction> request){
        service.updateCategory(request.getData().get(0));
        return new Response<>();
    }

    @DeleteMapping("/deleteCategory")
    public Response<Object> deleteCategory(@RequestParam(name = "hostRef") String hostRef,
                                           @RequestParam(name = "accountId") String accountId){
        service.deleteCategory(hostRef,accountId);
        return new Response<>();
    }
}
