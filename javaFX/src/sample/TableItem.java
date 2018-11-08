package sample;

public class TableItem {
    private String month;
    private double healthyNumberNoVacc;
    private double healthyNumberVacc;
    private double illNumberNoVacc;
    private double illNumberVacc;
    private double vaccNumber;
    private double mb;

    public TableItem() {
    }

    public double getHealthyNumberNoVacc() {
        return healthyNumberNoVacc;
    }

    public TableItem setHealthyNumberNoVacc(double healthyNumberNoVacc) {
        this.healthyNumberNoVacc = healthyNumberNoVacc;
        return this;
    }

    public double getHealthyNumberVacc() {
        return healthyNumberVacc;
    }

    public TableItem setHealthyNumberVacc(double healthyNumberVacc) {
        this.healthyNumberVacc = healthyNumberVacc;
        return this;
    }

    public String getMonth() {
        return month;
    }

    public TableItem setMonth(String month) {
        this.month = month;
        return this;
    }

    public double getIllNumberNoVacc() {
        return illNumberNoVacc;
    }

    public TableItem setIllNumberNoVacc(double illNumberNoVacc) {
        this.illNumberNoVacc = illNumberNoVacc;
        return this;
    }

    public double getIllNumberVacc() {
        return illNumberVacc;
    }

    public TableItem setIllNumberVacc(double illNumberVacc) {
        this.illNumberVacc = illNumberVacc;
        return this;
    }

    public double getVaccNumber() {
        return vaccNumber;
    }

    public TableItem setVaccNumber(double vaccNumber) {
        this.vaccNumber = vaccNumber;
        return this;
    }

    public double getMb() {
        return mb;
    }

    public TableItem setMb(double mb) {
        this.mb = mb;
        return this;
    }
}
