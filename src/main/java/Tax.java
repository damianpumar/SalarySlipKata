import java.math.BigDecimal;
import java.math.RoundingMode;

public class Tax {
    private double annualGrossSalary;

    private final double NO_TAXES = 0;
    private final double BASIC_TAX_PERCENTAGE = 0.20;
    private final double MIN_ANNUAL_GROSS_SALARY = 11000;
    private final double HIGHER_TAX_PERCENTAGE = 0.40;
    private final double MAX_ANNUAL_BASIC_GROSS_SALARY = 43000;

    public Tax(double annualGrossSalary) {
        this.annualGrossSalary = annualGrossSalary;
    }

    public double taxFreeAllowance() {
        return roundingDecimal(MIN_ANNUAL_GROSS_SALARY / 12);
    }

    public double taxableIncome() {
        double taxExcess = this.annualGrossSalary - MIN_ANNUAL_GROSS_SALARY;

        if (taxExcess > 0)
            return roundingDecimal(taxExcess / 12);

        return NO_TAXES;
    }

    public double taxPayable() {
        double taxPayable = NO_TAXES;
        double taxableAmmount = 0;

        double taxExcess = this.annualGrossSalary - MIN_ANNUAL_GROSS_SALARY;

        if (this.annualGrossSalary > MAX_ANNUAL_BASIC_GROSS_SALARY) {
            taxableAmmount = (taxExcess - 32000);

            taxPayable += roundingDecimal(taxableAmmount * HIGHER_TAX_PERCENTAGE);
        }

        if (this.annualGrossSalary > MIN_ANNUAL_GROSS_SALARY)
            taxPayable += roundingDecimal((taxExcess - taxableAmmount) * BASIC_TAX_PERCENTAGE);


        return roundingDecimal(taxPayable / 12);
    }

    private double roundingDecimal(double tax) {
        return BigDecimal.valueOf(tax)
                .setScale(2, RoundingMode.HALF_DOWN)
                .doubleValue();
    }
}
