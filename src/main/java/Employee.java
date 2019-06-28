import java.math.BigDecimal;

public class Employee {
    private final int id;
    private final String name;
    private final BigDecimal annualGrossSalary;

    public Employee(int id, String name, double annualGrossSalary) {
        this.id = id;
        this.name = name;
        this.annualGrossSalary = new BigDecimal(annualGrossSalary);
    }

    public int Id() {
        return this.id;
    }

    public String Name() {
        return this.name;
    }

    public BigDecimal AnnualGrossSalary() {
        return this.annualGrossSalary;
    }
}
