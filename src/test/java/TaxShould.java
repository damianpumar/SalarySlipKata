import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class TaxShould {

    @Test
    public void be_Zero_Percent_Taxable_Income_When_Salary_Is_Below_11000() {
        Tax tax = new Tax(10999);

        assertThat(tax.taxableIncome()).isEqualTo(0);
    }

    @Test
    public void be_Twenty_Percent_Taxes_When_Salary_Is_Above_11000() {
        Tax tax = new Tax(12000);

        assertThat(tax.taxFreeAllowance()).isEqualTo(916.67);
        assertThat(tax.taxableIncome()).isEqualTo(83.33);
        assertThat(tax.taxPayable()).isEqualTo(16.67);
    }
}
