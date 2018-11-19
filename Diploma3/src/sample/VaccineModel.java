package sample;

public class VaccineModel {

    private static double calculateNextS(double s, double i, double betta, double totalPopulation, double vParam){
        return s - betta * i / totalPopulation*s + i - vParam * s;
    }

    private static double calculateNextI(double s, double i, double betta, double totalPopulation){
        return betta * i / totalPopulation * s;
    }

    private static double calculateNextV(double s, double v, double vParam){
        return v + vParam*s;
    }

    public static RegionData calculateVacc(RegionData data){
        RegionData data1 = null;

        try {
            data1 = (RegionData) data.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        for (int i = 1; i < 12; i++) {
            data1.getS()[i] = calculateNextS(
                    data1.getS()[i-1],
                    data1.getI()[i-1],
                    data1.getIntensityInfluenzaTransmission()[i-1],
                    data1.getTotalPopulation(),
                    data1.getVaccParam()[i-1]
            );

            data1.getI()[i] = calculateNextI(
                    data1.getS()[i-1],
                    data1.getI()[i-1],
                    data1.getIntensityInfluenzaTransmission()[i-1],
                    data1.getTotalPopulation()
            );

            data1.getV()[i] = calculateNextV(
                    data1.getS()[i-1],
                    data1.getV()[i-1],
                    data1.getVaccParam()[i-1]
            );
        }

        return data1;
    }
}
