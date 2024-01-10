package HospitalManagamentSystem;

public class SilverPlan extends HealthInsurancePlan{
    public SilverPlan()
    {
        setCoverage(0.7);
    }


    @Override
    public double computeMonthlyPremium(double salary, int age, boolean smoking) {
        return 0.6*salary+getOfferedBy().computeMonthlyPremium(this,age,smoking);
    }
}
