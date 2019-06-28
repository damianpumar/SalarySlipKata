public class SalarySlipGenerator {
    private NationalInsuranceContribution nationalInsuranceContribution;
    private Tax tax;

    public SalarySlipGenerator(NationalInsuranceContribution nationalInsuranceContribution,
                               Tax tax) {
        this.nationalInsuranceContribution = nationalInsuranceContribution;
        this.tax = tax;
    }

    public SalarySlip generateFor(Employee employee) {
        return new SalarySlip(employee, this.nationalInsuranceContribution, this.tax);
    }
}
