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

    @Mock
    Tax tax;

    @Before
    public void Initialize() {
        nationalInsuranceContribution = mock(NationalInsuranceContribution.class);

        tax = mock(Tax.class);

        salarySlipGenerator = new SalarySlipGenerator(nationalInsuranceContribution, tax);
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

    @Test
    public void calculate_Salary_Slip_With_Tax() {
        final double TAX_FREE_ALLOWANCE = 916.67;
        final double TAXABLE_INCOME = 83.33;
        final double TAX_PAYABLE = 16.67;
        Employee employee = new Employee(12345, "Jonh J Doe", 5000);

        when(tax.taxFreeAllowance()).thenReturn(TAX_FREE_ALLOWANCE);
        when(tax.taxableIncome()).thenReturn(TAXABLE_INCOME);
        when(tax.taxPayable()).thenReturn(TAX_PAYABLE);

        SalarySlip salarySlip = salarySlipGenerator.generateFor(employee);

        assertThat(salarySlip.taxFreeAllowance()).isEqualTo(TAX_FREE_ALLOWANCE);
        assertThat(salarySlip.taxableIncome()).isEqualTo(TAXABLE_INCOME);
        assertThat(salarySlip.taxPayable()).isEqualTo(TAX_PAYABLE);
    }
}