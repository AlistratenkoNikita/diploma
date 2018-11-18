package sample;

public class RegionData {
    private double[] intensityInfluenzaTransmission;
    private double[] vaccParam;
    private double totalPopulation;
    private double startIllNumber;
    private double startHealthNumber;
    private double[] s;
    private double[] i;
    private double[] v;

    public static final String[] MONTHS = {
            "січень", "лютий", "березень",
            "квітень", "травень", "червень",
            "липень", "серпень", "вересень",
            "жовтень", "листопад", "грудень"
    };

    public RegionData(double[] intensityInfluenzaTransmission, double totalPopulation, double startIllNumber) {
        this.intensityInfluenzaTransmission = intensityInfluenzaTransmission;
        this.vaccParam = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        this.totalPopulation = totalPopulation;
        this.startIllNumber = startIllNumber;
        this.startHealthNumber = totalPopulation - startIllNumber;
        this.s = new double[]{startHealthNumber, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        this.i = new double[]{startIllNumber, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        this.v = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    }

    public RegionData(double[] intensityInfluenzaTransmission, double totalPopulation, double startIllNumber, double[] vaccParam) {
        this.intensityInfluenzaTransmission = intensityInfluenzaTransmission;
        this.vaccParam = vaccParam;
        this.totalPopulation = totalPopulation;
        this.startIllNumber = startIllNumber;
        this.startHealthNumber = totalPopulation - startIllNumber;
        this.s = new double[]{startHealthNumber, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        this.i = new double[]{startIllNumber, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        this.v = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    }

    public double[] getIntensityInfluenzaTransmission() {
        return intensityInfluenzaTransmission;
    }

    public double[] getVaccParam() {
        return vaccParam;
    }

    public double getTotalPopulation() {
        return totalPopulation;
    }

    public double getStartIllNumber() {
        return startIllNumber;
    }

    public double getStartHealthNumber() {
        return startHealthNumber;
    }

    public double[] getS() {
        return s;
    }

    public double[] getI() {
        return i;
    }

    public double[] getV() {
        return v;
    }

    public RegionData setIntensityInfluenzaTransmission(double[] intensityInfluenzaTransmission) {
        this.intensityInfluenzaTransmission = intensityInfluenzaTransmission;
        return this;
    }

    public RegionData setVaccParam(double[] vaccParam) {
        this.vaccParam = vaccParam;
        return this;
    }

    public RegionData setTotalPopulation(double totalPopulation) {
        this.totalPopulation = totalPopulation;
        return this;
    }

    public RegionData setStartIllNumber(double startIllNumber) {
        this.startIllNumber = startIllNumber;
        return this;
    }

    public RegionData setStartHealthNumber(double startHealthNumber) {
        this.startHealthNumber = startHealthNumber;
        return this;
    }

    public RegionData setS(double[] vacS) {
        this.s = vacS;
        return this;
    }

    public RegionData setI(double[] vacI) {
        this.i = vacI;
        return this;
    }

    public RegionData setV(double[] vacV) {
        this.v = vacV;
        return this;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new RegionData(
                this.intensityInfluenzaTransmission.clone(),
                this.totalPopulation,
                this.startIllNumber,
                this.vaccParam.clone());
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < 12; i++) {
            result.append(String.format("%s:  \ts = %.15f, \ti = %.15f\n", MONTHS[i], s[i], this.i[i]));
        }

        return result.toString();
    }
}
