import java.math.BigDecimal;
import java.math.RoundingMode;

public class SalarySlip {
    private final Employee employee;
    private final NationalInsuranceContribution nationalInsuranceContribution;
    private Tax tax;

    private final BigDecimal TWELVE_MONTHS = new BigDecimal(12);
    private final int PRECISION_GROSS_SALARY = 2;

    public SalarySlip(Employee employee,
                      NationalInsuranceContribution nationalInsuranceContribution,
                      Tax tax) {
        this.employee = employee;
        this.nationalInsuranceContribution = nationalInsuranceContribution;
        this.tax = tax;
    }

    public int employeeId() {
        return this.employee.Id();
    }

    public String employeeName() {
        return this.employee.Name();
    }

    public BigDecimal monthlyGrossSalary() {
        return this.employee.AnnualGrossSalary().divide(TWELVE_MONTHS, PRECISION_GROSS_SALARY, RoundingMode.HALF_DOWN);
    }

    public double nationalInsuranceContribution() {
        return this.nationalInsuranceContribution.contribution();
    }

    public double taxFreeAllowance() {
        return this.tax.taxFreeAllowance();
    }

    public double taxableIncome() {
        return this.tax.taxableIncome();
    }

    public double taxPayable() {
        return this.tax.taxPayable();
    }
}
