package banking.service.FinancialManagementService.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class StatisticOverview {
    private String customerId;

    private String type;

    private double amount;

    private String period;
}
