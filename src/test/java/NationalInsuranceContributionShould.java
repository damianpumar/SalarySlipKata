import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class NationalInsuranceContributionShould {
    @Test
    public void be_zero_percent_when_annual_salary_slip_is_below_8060() {
        NationalInsuranceContribution nationalInsuranceContribution = new NationalInsuranceContribution(8059);

        assertThat(nationalInsuranceContribution.contribution()).isEqualTo(0);
    }

    @Test
    public void be_zero_percent_when_annual_salary_slip_is_equal_8060() {
        NationalInsuranceContribution nationalInsuranceContribution = new NationalInsuranceContribution(8060);

        assertThat(nationalInsuranceContribution.contribution()).isEqualTo(0);
    }

    @Test
    public void be_twelve_percent_when_annual_salary_slip_is_above_9060() {
        NationalInsuranceContribution nationalInsuranceContribution = new NationalInsuranceContribution(9060);

        assertThat(nationalInsuranceContribution.contribution()).isEqualTo(10);
    }
}
