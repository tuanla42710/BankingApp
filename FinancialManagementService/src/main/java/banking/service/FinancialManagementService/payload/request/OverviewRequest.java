package banking.service.FinancialManagementService.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OverviewRequest {

    private String requestId;

    private String sessionId;

    private String customerId;

    private String fromDate;

    private String toDate;

    private String period;

}
