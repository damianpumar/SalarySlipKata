import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class SalarySlipGeneratorShould {

    @Test
    public void calculate_Salary_Slip_Of_5k_Income_Employee() {
        SalarySlipGenerator salarySlipGenerator = new SalarySlipGenerator();
        Employee employee = new Employee(12345, "Jonh J Doe", 5000);
        SalarySlip salarySlip = salarySlipGenerator.generateFor(employee);

        assertThat(salarySlip.EmployeeId()).isEqualTo(employee.Id());
        assertThat(salarySlip.EmployeeName()).isEqualTo(employee.Name());
        assertThat(salarySlip.MonthlyGrossSalary()).isEqualTo(BigDecimal.valueOf(416.67));
    }
}