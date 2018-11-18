public class NoVaccineModel {

    private double calculateNextS(double s, double i, double betta, double totalPopulation){
        return s - betta * i / totalPopulation * s + i;
    }

    private double calculateNextI(double s, double i, double betta, double totalPopulation){
        return betta * i / totalPopulation * s;
    }

    public RegionData calculateVacc(RegionData data){
        RegionData dataTmp = null;
        try {
            dataTmp = (RegionData) data.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        for (int i = 1; i < 12; i++) {
            dataTmp.getS()[i] = calculateNextS(
                    dataTmp.getS()[i-1],
                    dataTmp.getI()[i-1],
                    dataTmp.getIntensityInfluenzaTransmission()[i-1],
                    dataTmp.getTotalPopulation()
            );

            dataTmp.getI()[i] = calculateNextI(
                    dataTmp.getS()[i-1],
                    dataTmp.getI()[i-1],
                    dataTmp.getIntensityInfluenzaTransmission()[i-1],
                    dataTmp.getTotalPopulation()
            );
        }

        return dataTmp;
    }
}
