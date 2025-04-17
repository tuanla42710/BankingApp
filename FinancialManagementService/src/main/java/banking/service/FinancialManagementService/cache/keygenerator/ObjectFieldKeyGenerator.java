package banking.service.FinancialManagementService.cache.keygenerator;

import banking.service.FinancialManagementService.payload.request.OverviewRequest;
import org.hibernate.sql.ast.tree.expression.Over;
import org.springframework.cache.interceptor.KeyGenerator;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

public class ObjectFieldKeyGenerator implements KeyGenerator {
    @Override
    public Object generate(Object target, Method method, Object... params) {
        return Arrays.stream(params)
                .map(param -> {
                    if (param instanceof OverviewRequest){
                        OverviewRequest request = (OverviewRequest) param;
                        return String.format("%s:%s:%s:%s",
                                request.getCustomerId(),
                                request.getFromDate(),
                                request.getToDate(),
                                request.getPeriod());
                    }
                    return param.toString();
                }).collect(Collectors.joining(":"));
    }
}
