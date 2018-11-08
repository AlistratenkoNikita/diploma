package sample.math;

public class VaccineModel {

    public static double calculateNextS(double s, double i, double betta, double totalPopulation, double vParam){
        return s - (((betta * i) / totalPopulation)*s + i) - (vParam * s);
    }

    public static double calculateNextI(double s, double i, double betta, double totalPopulation){
        return ((betta*i)/totalPopulation)*s;
    }

    public static double calculateNextV(double s, double v, double vParam){
        return v + vParam*s;
    }
}
