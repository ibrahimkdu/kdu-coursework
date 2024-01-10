package HospitalManagamentSystem;

import LogBack.LogBack;

public class Patient extends User{

    private long patientId;
    private HealthInsurancePlan plan;

    public HealthInsurancePlan getInsurancePlan() {
        return plan;
    }

    public void setInsurancePlan(HealthInsurancePlan plan) {
        this.plan = plan;
    }

    public long getPatientId() {
        return patientId;
    }
    public void setPatientId(long patientId) {
        this.patientId = patientId;
    }

    public static void main(String[] Args )
    {
        Patient patient=new Patient();
        patient.setPatientId(1);
        HealthInsurancePlan plan=new PlatinumPlan();
        patient.setInsurancePlan(plan);
        //calling billing class function computePaymentAmpunt
        double amount=1000;
        double[] payments=Billing.computePaymentAmount(patient,amount);
        String answer = String.format("Payment by insurance %f", payments[0]);
        LogBack.slf4jLogger.debug(answer);
        answer = String.format("Payment by patient %f", payments[1]);
        LogBack.slf4jLogger.debug(answer);

    }

}
