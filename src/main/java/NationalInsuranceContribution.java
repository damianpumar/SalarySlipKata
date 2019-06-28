import java.math.BigDecimal;
import java.math.RoundingMode;

public class NationalInsuranceContribution {
    private final BigDecimal annualGrossSalary;
    private final BigDecimal MONTHS_YEAR = new BigDecimal(12);
    private final BigDecimal BASIC_NATIONAL_INSURANCE_CONTRIBUTION_PERCENTAGE = new BigDecimal(0.12);
    private final BigDecimal HIGHER_NATIONAL_INSURANCE_CONTRIBUTION_PERCENTAGE = new BigDecimal(0.02);
    private final BigDecimal MAX_SALARY_WITHOUT_CONTRIBUTION = new BigDecimal(8060);
    private final BigDecimal MAX_SALARY_WITH_BASIC_CONTRIBUTION = new BigDecimal(43000);
    private final int PRECISION_DECIMAL = 2;
    private final RoundingMode ROUNDING_METHOD = RoundingMode.HALF_DOWN;
    private final double NO_CONTRIBUTION = 0;

    public NationalInsuranceContribution(double annualGrossSalary) {
        this.annualGrossSalary = new BigDecimal(annualGrossSalary);
    }

    public double contribution() {
        double contribution = NO_CONTRIBUTION;

        if (this.annualGrossSalary.doubleValue() > MAX_SALARY_WITHOUT_CONTRIBUTION.doubleValue())
            contribution += this.calculateBasicContribution();

        if (this.annualGrossSalary.doubleValue() > MAX_SALARY_WITH_BASIC_CONTRIBUTION.doubleValue())
            contribution += this.calculateHigherContribution();

        return roundingTwoDecimals(contribution);
    }

    private double calculateBasicContribution() {
        BigDecimal salaryExcess = this.annualGrossSalary.subtract(MAX_SALARY_WITHOUT_CONTRIBUTION);

        return multiply(salaryExcess, BASIC_NATIONAL_INSURANCE_CONTRIBUTION_PERCENTAGE).doubleValue();
    }

    private double calculateHigherContribution() {
        BigDecimal salaryExcess = this.annualGrossSalary.subtract(MAX_SALARY_WITH_BASIC_CONTRIBUTION);

        return multiply(salaryExcess, HIGHER_NATIONAL_INSURANCE_CONTRIBUTION_PERCENTAGE).doubleValue();
    }

    private BigDecimal multiply(BigDecimal amount, BigDecimal multiplicand) {
        return amount.divide(MONTHS_YEAR, PRECISION_DECIMAL, ROUNDING_METHOD)
                .multiply(multiplicand);
    }

    private double roundingTwoDecimals(double number) {
        return BigDecimal.valueOf(number)
                .setScale(PRECISION_DECIMAL, ROUNDING_METHOD)
                .doubleValue();
    }
}
