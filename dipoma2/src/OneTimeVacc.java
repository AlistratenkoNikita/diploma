public class OneTimeVacc {
    public static double[] findBestMonth(RegionData data){
        double bestGV = 0;
        double[] bestVacc = null;
        VaccineModel vacModel = new VaccineModel();
        NoVaccineModel noVacModel = new NoVaccineModel();

        for (int i = 0; i < 12; i++) {
            for (int j = 1; j <= 100; j++) {
                RegionData dataNoVacc = null;
                RegionData dataVacc = null;
                try {
                    dataNoVacc = (RegionData) data.clone();
                    dataVacc = (RegionData) data.clone();
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }

                double[] vaccParam = new double[12];
                vaccParam[i] = (double) j * 0.01d;
                dataVacc.setVaccParam(vaccParam);
                RegionData resultNoVacc = noVacModel.calculateVacc(dataNoVacc);
                RegionData resultVacc = vacModel.calculateVacc(dataVacc);
                double gv = calculateGV(resultNoVacc, resultVacc);

                if (gv > bestGV){
                    bestGV = gv;
                    bestVacc = vaccParam.clone();
                }

            }
        }

        return bestVacc;
    }

    private static double calculateGV(RegionData noVacc, RegionData vacc){
        double diffS = 0;
        double totalV = 0;

        for (int i = 0; i < noVacc.getS().length; i++) {
            diffS += Math.abs(noVacc.getI()[i] - vacc.getI()[i]);
            totalV += vacc.getV()[i];
        }

        return (diffS / totalV) * vacc.getTotalPopulation();
    }
}
