package sample.math;

public class NoVaccineModel {

    public static double calculateNextS(double s, double i, double betta, double totalPopulation){
        return s - betta * i / totalPopulation * s + i;
    }

    public static double calculateNextI(double s, double i, double betta, double totalPopulation){
        return betta * i / totalPopulation * s;
    }
}
