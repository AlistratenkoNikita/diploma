package sample;

import java.util.Arrays;

public class TwoTimesVacc {

    public TwoTimesVacc() {
    }

    public double[] findBestMonth(RegionData data){
        double bestGV = 0;
        double[] bestVacc = new double[12];

        double[] arr = new double[12];
        double max = 1;

        for (double val = data.getVacStep(); val <= max ; val+=data.getVacStep()) {
            for (int i = 0; i < arr.length; i++) {
                double[] ar1 = arr.clone();
//                ar1[i] = round(val, 2);
                ar1[i] = val;
                for (int j = i+1; j < arr.length-1; j++) {
                    double[] cl2 = ar1.clone();
//                    cl2[j] = round(val, 2);
                    cl2[j] = val;
                    RegionData dataNoVacc = null;
                    RegionData dataVacc = null;
                    try {
                        dataNoVacc = (RegionData) data.clone();
                        dataVacc = (RegionData) data.clone();
                    } catch (CloneNotSupportedException e) {
                        e.printStackTrace();
                    }

                    dataVacc.setVaccParam(cl2);
                    RegionData resultNoVacc = NoVaccineModel.calculateVacc(dataNoVacc);
                    RegionData resultVacc = VaccineModel.calculateVacc(dataVacc);
                    double gv = calculateGV(resultNoVacc, resultVacc);

                    if (gv > bestGV){
                        bestGV = gv;
                        bestVacc = cl2.clone();
                    }
                }
            }
        }

        return bestVacc;
    }

    public double calculateGV(RegionData noVacc, RegionData vacc){
        double diffS = 0;
        double totalV = 0;

        for (int i = 0; i < noVacc.getS().length; i++) {
            diffS += Math.abs(noVacc.getI()[i] - vacc.getI()[i]);
            totalV += vacc.getV()[i];
        }

        return (diffS / totalV) * vacc.getTotalPopulation();
    }

    private double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
