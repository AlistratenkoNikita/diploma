package sample;

public class OneTimeVacc {

    public OneTimeVacc(){
    }

    public double[] findBestMonth(RegionData data){
        double bestGV = 0;
        double[] bestVacc = new double[12];

        for (int i = 0; i < 12; i++) {
            for (int j = 1; j <= 1.0 / data.getVacStep(); j++) {
                RegionData dataNoVacc = null;
                RegionData dataVacc = null;
                try {
                    dataNoVacc = (RegionData) data.clone();
                    dataVacc = (RegionData) data.clone();
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }

                double[] vaccParam = new double[12];
                vaccParam[i] = (double) j * data.getVacStep();
                dataVacc.setVaccParam(vaccParam);
                RegionData resultNoVacc = NoVaccineModel.calculateVacc(dataNoVacc);
                RegionData resultVacc = VaccineModel.calculateVacc(dataVacc);
                double gv = calculateGV(resultNoVacc, resultVacc);

                if (gv > bestGV){
                    bestGV = gv;
                    bestVacc = vaccParam.clone();
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
}
