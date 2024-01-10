package HospitalManagamentSystem;


public class BlueCrossBlueShield implements InsuranceBrand {
    public double computeMonthlyPremium(HealthInsurancePlan insurancePlan, int age, boolean smoking) {
        double monthlyPremium = 0.0;
        if (insurancePlan instanceof PlatinumPlan) {
            if (smoking) {
                monthlyPremium += 100.0;
            }
            if (age > 55) {
                monthlyPremium += 200.0;
            }
        } else if (insurancePlan instanceof GoldPlan) {
            if (smoking) {
                monthlyPremium+= 90.0;
            }
            if (age > 55) {
                monthlyPremium += 150.0;
            }
        } else if (insurancePlan instanceof SilverPlan) {
            if (smoking) {
                monthlyPremium += 80.0;
            }
            if (age > 55) {
                monthlyPremium+= 100.0;
            }
        } else {
            if (smoking) {
                monthlyPremium += 70.0;
            }
            if (age > 55) {
                monthlyPremium += 50.0;
            }
        }
        return monthlyPremium;
    }
    public static void main(String[] args) {
        //declaring a user of staff type
        User staffEmployee = new User();
        //declaring an insurance brand of type BLueCrossBlueShield
        InsuranceBrand insuranceBrand = new BlueCrossBlueShield();
        HealthInsurancePlan insurancePlan = new PlatinumPlan();
        insurancePlan.setOfferedBy(insuranceBrand);
        staffEmployee.setInsurancePlan(insurancePlan);
        String answer = String.format("the answer %s", insurancePlan.computeMonthlyPremium(5000, 56, true));
        LogBack.slf4jLogger.debug(answer);
       
    }
}
