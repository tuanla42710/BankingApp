package banking.service.FinancialManagementService.query;

import java.util.ArrayList;
import java.util.List;

public class QueryFormatter {
    public String format(String sql, ArrayList<Object> params) {
        StringBuilder formattedQuery = new StringBuilder();
        int paramIndex = 0;
        for (int i = 0; i < sql.length(); i++) {
            char c = sql.charAt(i);
            if (c == '?' && paramIndex < params.size()) {
                Object param = params.get(paramIndex++);
                formattedQuery.append(formatParam(param));
            } else {
                formattedQuery.append(c);
            }
        }
        return formattedQuery.toString();
    }

    private String formatParam(Object param) {
        if (param == null) {
            return "NULL";
        } else if (param instanceof String || param instanceof Character) {
            return "'" + param.toString().replace("'", "''") + "'";
        } else {
            return param.toString();
        }
    }
}
