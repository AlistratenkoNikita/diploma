import java.util.Arrays;

public class Main {

    private static double[] noVacS = Data.NO_VACCINE_S;
    private static double[] noVacI = Data.NO_VACCINE_I;

    private static double[] vacS = Data.VACCINE_S;
    private static double[] vacI = Data.VACCINE_I;
    private static double[] vacV = Data.VACCINE_V;
    private static double[] vacVParam = Data.VACCINE_V_PARAM;

    public static void main(String[] args) {
        for (int i = 1; i < 12; i++) {
            noVacS[i] = NoVaccineModel.calculateNextS(
                    noVacS[i-1],
                    noVacI[i-1],
                    Data.INTENSITY_INFLUENZA_TRANSMISSION[i-1],
                    Data.TOTAL_POPULATION
            );

            noVacI[i] = NoVaccineModel.calculateNextI(
                    noVacS[i-1],
                    noVacI[i-1],
                    Data.INTENSITY_INFLUENZA_TRANSMISSION[i-1],
                    Data.TOTAL_POPULATION
            );

            vacS[i] = VaccineModel.calculateNextS(
                    vacS[i-1],
                    vacI[i-1],
                    Data.INTENSITY_INFLUENZA_TRANSMISSION[i-1],
                    Data.TOTAL_POPULATION,
                    Data.VACCINE_V_PARAM[i-1]
            );

            vacI[i] = VaccineModel.calculateNextI(
                    vacS[i-1],
                    vacI[i-1],
                    Data.INTENSITY_INFLUENZA_TRANSMISSION[i-1],
                    Data.TOTAL_POPULATION
            );

            vacV[i] = VaccineModel.calculateNextV(
                    vacS[i-1],
                    vacV[i-1],
                    Data.VACCINE_V_PARAM[i-1]
            );
        }

        System.out.println("БЕЗ ВАКЦИНАЦИИ");
        for (int i = 0; i < 12; i++) {
            System.out.printf("%s:  \ts = %.10f, \ti = %.10f\n", Data.MONTHS[i], noVacS[i], noVacI[i]);
        }

        System.out.println("C ВАКЦИНАЦИЕЙ");
        for (int i = 0; i < 12; i++) {
            System.out.println(Data.MONTHS[i] + ":  \ts = " + vacS[i] + ",   \ti = " + vacI[i] + ",  \tv = " + vacV[i]);
        }

        System.out.println("ГРАНИЧНАЯ ВЫГОДА");
        for (int i = 0; i < 12; i++) {
            System.out.println(Data.MONTHS[i] + ":  \t MB = " + Math.abs(noVacI[i] - vacI[i])*vacV[i]);
        }
    }
}
