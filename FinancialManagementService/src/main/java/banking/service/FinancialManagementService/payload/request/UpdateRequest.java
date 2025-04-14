package banking.service.FinancialManagementService.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UpdateRequest<T> {
    private String requestId;

    private String sessionId;

    private List<T> data;
}
