import java.math.BigDecimal;
import java.math.RoundingMode;

public class NationalInsuranceContribution {
    private final BigDecimal annualGrossSalary;
    private final BigDecimal MONTHS_YEAR = new BigDecimal(12);
    private final BigDecimal NATIONAL_INSURANCE_CONTRIBUTION_PERCENTAGE = new BigDecimal(0.12);
    private final BigDecimal MAX_SALARY_WITHOUT_CONTRIBUTION = new BigDecimal(8060);
    private final int PRECISION_DECIMAL = 2;
    private final RoundingMode ROUNDING_METHOD = RoundingMode.HALF_DOWN;

    public NationalInsuranceContribution(double annualGrossSalary) {
        this.annualGrossSalary = new BigDecimal(annualGrossSalary);
    }

    public double contribution() {
        BigDecimal salaryExcess = this.annualGrossSalary.subtract(MAX_SALARY_WITHOUT_CONTRIBUTION);

        if (salaryExcess.doubleValue() > 0) {
            return salaryExcess.divide(MONTHS_YEAR, PRECISION_DECIMAL, ROUNDING_METHOD)
                    .multiply(NATIONAL_INSURANCE_CONTRIBUTION_PERCENTAGE)
                    .setScale(PRECISION_DECIMAL, ROUNDING_METHOD)
                    .doubleValue();
        }

        return 0;
    }
}
