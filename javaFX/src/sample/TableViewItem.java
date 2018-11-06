package sample;

public class TableViewItem {
    private String month;
    private double healthyNumber;
    private double illNumber;
    private double vaccNumber;
    private double mb;

    public TableViewItem() {
    }

    public String getMonth() {
        return month;
    }

    public TableViewItem setMonth(String month) {
        this.month = month;
        return this;
    }

    public double getMb() {
        return mb;
    }

    public TableViewItem setMb(double mb) {
        this.mb = mb;
        return this;
    }

    public double getHealthyNumber() {
        return healthyNumber;
    }

    public TableViewItem setHealthyNumber(double healthyNumber) {
        this.healthyNumber = healthyNumber;
        return this;
    }

    public double getIllNumber() {
        return illNumber;
    }

    public TableViewItem setIllNumber(double illNumber) {
        this.illNumber = illNumber;
        return this;
    }

    public double getVaccNumber() {
        return vaccNumber;
    }

    public TableViewItem setVaccNumber(double vaccNumber) {
        this.vaccNumber = vaccNumber;
        return this;
    }
}
