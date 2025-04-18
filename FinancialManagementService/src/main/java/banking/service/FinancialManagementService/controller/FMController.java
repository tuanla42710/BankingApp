package banking.service.FinancialManagementService.controller;

import banking.service.FinancialManagementService.config.JwtUtil;
import banking.service.FinancialManagementService.model.CategorizedTransaction;
import banking.service.FinancialManagementService.model.StatisticOverview;
import banking.service.FinancialManagementService.model.User;
import banking.service.FinancialManagementService.payload.dto.AuthRequest;
import banking.service.FinancialManagementService.payload.dto.AuthResponse;
import banking.service.FinancialManagementService.payload.request.OverviewRequest;
import banking.service.FinancialManagementService.payload.request.UpdateRequest;
import banking.service.FinancialManagementService.payload.response.Response;
import banking.service.FinancialManagementService.repository.UserRepository;
import banking.service.FinancialManagementService.service.CategoryService;
import ch.qos.logback.core.encoder.EchoEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Stack;

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

    @Cacheable(value = "StatisticOverview",  keyGenerator = "overviewGenerator")
    @PostMapping("/getStatisticOverview")
    public Response<StatisticOverview> getStatisticOverview(@RequestBody OverviewRequest request){
        try {
            List<StatisticOverview> overviews = service.getStatisticOverview(request);
            return new Response<>(200, false, 0,null, overviews);
        } catch (Exception e){
            return new Response<>(404, true, e.hashCode(), e.getMessage(),null);
        }

    }
}
