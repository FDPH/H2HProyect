package parameta.gateway.dto.request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import parameta.gateway.dto.DocumentType;

import java.time.LocalDate;

public record EmployeeRequest(
        @NotBlank(message = "name cannot be blank")
        @Size(max = 150, message = "name must be less than 150 characters")
        String name,

        @NotBlank(message = "last name cannot be blank")
        @Size(max = 150, message = "last names must be less than 150 characters")
        String lastName,

        @NotNull(message = "document type cannot be blank")
        DocumentType documentType,

        @NotBlank(message = "id number cannot be blank")
        @Size(max = 15, message = "id number must be less than 15 characters")
        String idNumber,

        @NotNull(message = "dateOfBirth cannot be blank")
        LocalDate dateOfBirth,

        @NotNull(message = "dateOfJoiningCompany cannot be blank")
        LocalDate dateOfJoiningCompany,

        @NotBlank(message = "position cannot be blank")
        @Size(max = 50, message = "position must be less than 50 characters")
        String position,

        @NotNull(message = "salary cannot be blank")
        @Digits(integer = 14, fraction = 2, message = "Salary must be a valid monetary value with up to 14 digits and 2 decimals")
        Double salary
) {
}
