package banking.service.FinancialManagementService.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class StatisticOverview implements Serializable {
    private String customerId;

    private String type;

    private double amount;

    private String period;
}
