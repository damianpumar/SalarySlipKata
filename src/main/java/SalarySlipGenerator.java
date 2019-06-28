public class SalarySlipGenerator {
    private NationalInsuranceContribution nationalInsuranceContribution;

    public SalarySlipGenerator(NationalInsuranceContribution nationalInsuranceContribution) {

        this.nationalInsuranceContribution = nationalInsuranceContribution;
    }

    public SalarySlip generateFor(Employee employee) {
        return new SalarySlip(employee, this.nationalInsuranceContribution);
    }
}
