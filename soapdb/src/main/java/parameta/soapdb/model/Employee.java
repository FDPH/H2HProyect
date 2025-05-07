package parameta.soapdb.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import parameta.soapdb.DocumentType;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(name = "name", nullable = false)
    private String name;

    @Setter
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Setter
    @Column(name = "document_type", nullable = false)
    private DocumentType documentType;

    @Setter
    @Column(name = "id_number", nullable = false)
    private String idNumber;

    @Setter
    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Setter
    @Column(name = "date_of_joining_company", nullable = false)
    private LocalDate dateOfJoiningCompany;

    @Setter
    @Column(name = "position", nullable = false)
    private String position;

    @Setter
    @Column(name = "salary", nullable = false, precision = 14, scale = 2)
    private BigDecimal salary;

    public Employee(String name, String lastName, DocumentType documentType, String idNumber, LocalDate dateOfBirth,
                    LocalDate dateOfJoiningCompany, String position, BigDecimal salary) {
        this.name = name;
        this.lastName = lastName;
        this.documentType = documentType;
        this.idNumber = idNumber;
        this.dateOfBirth = dateOfBirth;
        this.dateOfJoiningCompany = dateOfJoiningCompany;
        this.position = position;
        this.salary = salary;
    }
}
