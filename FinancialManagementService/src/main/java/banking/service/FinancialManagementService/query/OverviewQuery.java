package banking.service.FinancialManagementService.query;

import banking.service.FinancialManagementService.payload.request.OverviewRequest;
import org.hibernate.sql.ast.tree.expression.Over;

import java.util.ArrayList;

public class OverviewQuery {
    String sql = """
            WITH category_transaction AS (
                SELECT a.trans_id, b.account_number, a.customer_id, a.amount, a.last_update as exec_date, coalesce(b.category, a.category) category
                FROM (SELECT trans_id, account_number, customer_id, amount, category, last_update
                      FROM bank_transaction where last_update >= ? and last_update <= ? and customer_id = ?) a
                LEFT JOIN (
                        SELECT trans_id, account_number, customer_id, category, amount , version
                        FROM (
                        SELECT trans_id, account_number, customer_id, category, amount , version,
                        row_number() over (partition by trans_id order by version desc) as rn, exec_date
                        FROM TRANSACTION_CATEGORY ) X \s
                        WHERE rn = 1 and exec_date >= ? and exec_date <= ? and customer_id = ?) b ON a.trans_id = b.trans_id )
            SELECT customer_id, income_or_expense, sum(amount) as amount %s
            FROM category_transaction a
            LEFT JOIN categories b ON a.category = b.name_en
            GROUP BY customer_id, income_or_expense %s
            """;

    OverviewRequest request;

    public OverviewQuery(OverviewRequest request) {
        this.request = request;
    }

    public String formatDate(){
        if (request.getPeriod() == null ) {
            return "";
        }
        return switch (request.getPeriod()) {
            case "Y" -> " , year(exec_date)";
            case "M" -> " , concat(year(exec_date), '-', month(exec_date)) ";
            case "D" -> " , exec_date";
            default -> ", exec_date";
        };
    }

    public String buildQuery(){
        String dateBuilder = formatDate();
        String sqlQuery = null;
        if (!dateBuilder.isEmpty()){
            sqlQuery = String.format(sql, dateBuilder + " as exec_date", dateBuilder );
        } else {
            sqlQuery = String.format(sql, dateBuilder, dateBuilder);
        }
        QueryFormatter formatter = new QueryFormatter();

        ArrayList<Object> params = new ArrayList<>();

        params.add(request.getFromDate());
        params.add(request.getToDate());
        params.add(request.getCustomerId());
        params.add(request.getFromDate());
        params.add(request.getToDate());
        params.add(request.getCustomerId());

        return formatter.format(sqlQuery, params);
    }

    public static void main(String[] args){
        OverviewRequest request1 = new OverviewRequest("123","123","123456789","2024-01-01","2025-12-31","M");
        OverviewQuery query = new OverviewQuery(request1);
        System.out.print(query.buildQuery());
    }


}
