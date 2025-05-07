package parameta.gateway.dto.response;

import parameta.gateway.dto.Duration;

public record EmployeeResponse(
        Duration lengthOfTmeInCompany,
        Duration age
) {
}
