import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SalarySlipGeneratorShould {

    private SalarySlipGenerator salarySlipGenerator;

    @Mock
    NationalInsuranceContribution nationalInsuranceContribution;

    @Before
    public void Initialize() {
        nationalInsuranceContribution = mock(NationalInsuranceContribution.class);

        salarySlipGenerator = new SalarySlipGenerator(nationalInsuranceContribution);
    }

    @Test
    public void calculate_Salary_Slip_Of_5k_Income_Employee() {
        Employee employee = new Employee(12345, "Jonh J Doe", 5000);
        SalarySlip salarySlip = salarySlipGenerator.generateFor(employee);

        assertThat(salarySlip.employeeId()).isEqualTo(employee.Id());
        assertThat(salarySlip.employeeName()).isEqualTo(employee.Name());
        assertThat(salarySlip.monthlyGrossSalary()).isEqualTo(BigDecimal.valueOf(416.67));
    }

    @Test
    public void calculate_Salary_Slip_With_National_Insurance_Contribution() {
        final double NATIONAL_INSURANCE_CONTRIBUTION = 950.32;
        Employee employee = new Employee(12345, "Jonh J Doe", 5000);
        when(nationalInsuranceContribution.contribution()).thenReturn(NATIONAL_INSURANCE_CONTRIBUTION);

        SalarySlip salarySlip = salarySlipGenerator.generateFor(employee);

        assertThat(salarySlip.nationalInsuranceContribution()).isEqualTo(NATIONAL_INSURANCE_CONTRIBUTION);
    }
}