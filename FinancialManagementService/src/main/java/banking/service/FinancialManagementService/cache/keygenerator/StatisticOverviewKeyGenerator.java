package banking.service.FinancialManagementService.cache.keygenerator;

import banking.service.FinancialManagementService.payload.request.OverviewRequest;
import org.hibernate.sql.ast.tree.expression.Over;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;


@Component("overviewGenerator")
public class StatisticOverviewKeyGenerator implements KeyGenerator {
    @Override
    public Object generate(Object target, Method method, Object... params) {
        return Arrays.stream(params)
                .map(param -> {
                    if (param instanceof OverviewRequest request){
                        return String.format("%s:%s:%s:%s",
                                request.getCustomerId(),
                                request.getFromDate(),
                                request.getToDate(),
                                request.getPeriod());
                    }
                    return param.toString();
                }).collect(Collectors.joining());
    }
}
