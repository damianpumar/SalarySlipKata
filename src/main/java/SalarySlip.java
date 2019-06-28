import java.math.BigDecimal;
import java.math.RoundingMode;

public class SalarySlip {
    private final Employee employee;
    private BigDecimal TWELVE_MONTHS = new BigDecimal(12);
    private int PRECISION_GROSS_SALARY = 2;

    public SalarySlip(Employee employee) {
        this.employee = employee;
    }

    public int EmployeeId() {
        return this.employee.Id();
    }

    public String EmployeeName() {
        return this.employee.Name();
    }

    public BigDecimal MonthlyGrossSalary() {
        return this.employee.AnnualGrossSalary().divide(TWELVE_MONTHS, PRECISION_GROSS_SALARY, RoundingMode.HALF_DOWN);
    }
}
