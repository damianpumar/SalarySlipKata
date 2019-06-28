import java.math.BigDecimal;
import java.math.RoundingMode;

public class Tax {
    private double annualGrossSalary;

    private final double NO_TAXES = 0;
    private final double TAX_PERCENTAGE = 0.20;
    private final double MIN_ANNUAL_GROSS_SALARY = 11000;

    public Tax(double annualGrossSalary) {
        this.annualGrossSalary = annualGrossSalary;
    }

    public double taxableIncome() {
        double taxExcess = calculateTaxExcess();

        if (taxExcess > 0)
            return roundingDecimal(taxExcess / 12);

        return NO_TAXES;
    }

    public double taxFreeAllowance() {
        double taxFreeAllowance = this.calculateTaxExcess() - this.taxableIncome();

        if (taxFreeAllowance > 0)
            return roundingDecimal(taxFreeAllowance);

        return NO_TAXES;
    }

    public double taxPayable() {
        double taxPayable = this.taxableIncome();

        if (taxPayable > 0)
            return roundingDecimal(taxPayable * TAX_PERCENTAGE);

        return NO_TAXES;
    }

    private double roundingDecimal(double tax) {
        return BigDecimal.valueOf(tax)
                .setScale(2, RoundingMode.HALF_DOWN)
                .doubleValue();
    }

    private double calculateTaxExcess() {
        return this.annualGrossSalary - MIN_ANNUAL_GROSS_SALARY;
    }
}
