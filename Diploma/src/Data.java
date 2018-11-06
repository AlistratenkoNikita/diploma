public class Data {
    public static final double TOTAL_POPULATION = 100_000;
    private static final double START_SICK_NUMBER = 62.6080735298626;
    private static final double START_HEALTHY_NUMBER = TOTAL_POPULATION - START_SICK_NUMBER;

    public static final double[] INTENSITY_INFLUENZA_TRANSMISSION = {
            0.880432683,
            0.151849973,
            0.034879461,
            0.243768939,
            0.222849328,
            0.789035533,
            0.517481656,
            0.676715936,
            19.32819258,
            5.549064378,
            94.32545033,
            0.743446325
    };

    public static final String[] MONTHS = {
            "січень",
            "лютий",
            "березень",
            "квітень",
            "травень",
            "червень",
            "липень",
            "серпень",
            "вересень",
            "жовтень",
            "листопад",
            "грудень"
    };


    public static final double[] NO_VACCINE_S = {START_HEALTHY_NUMBER,0,0,0,0,0,0,0,0,0,0,0};
    public static final double[] NO_VACCINE_I = {START_SICK_NUMBER,0,0,0,0,0,0,0,0,0,0,0};

    public static final double[] VACCINE_S = {START_HEALTHY_NUMBER,0,0,0,0,0,0,0,0,0,0,0};
    public static final double[] VACCINE_I = {START_SICK_NUMBER,0,0,0,0,0,0,0,0,0,0,0};
    public static final double[] VACCINE_V = {0,0,0,0,0,0,0,0,0,0,0,0};
    public static final double[] VACCINE_V_PARAM = {1,0,0,0,0,0,0,0,0,0,0,0};
}
