package HospitalManagamentSystem;

public class GoldPlan extends HealthInsurancePlan{
    public GoldPlan()
    {
        setCoverage(0.8);
    }


    @Override
    public double computeMonthlyPremium(double salary, int age, boolean smoking) {
        return 0.7*salary+getOfferedBy().computeMonthlyPremium(this,age,smoking);
    }
}
