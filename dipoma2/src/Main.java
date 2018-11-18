import javafx.scene.chart.LineChart;
import javafx.scene.control.TableView;

import java.util.Arrays;

public class Main {
    static RegionData vinnuzyaData = new RegionData(
            new double[]{0.24206139118118800000,
                         0.08068930919722430000,
                         0.00079011172380248200,
                         0.98549656209040700000,
                         0.98545092983357900000,
                         0.98523924553809200000,
                         0.98508208083816600000,
                         0.98541877421964000000,
                         122.93232708553100000000,
                         2.50173930649855000000,
                         84.18243155859050000000,
                         3.03723006266924000000
            },
            100000,
            0.00064172603723881900,
            new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
            );

    public static void main(String[] args) {
//        double[] bestMonth = OneTimeVacc.findBestMonth(vinnuzyaData);
//        System.out.println(Arrays.toString(bestMonth));

        for (int numberOfVacc = 1; numberOfVacc <= 12; numberOfVacc++) {
            for (double step = 0; step <= 1; step+=0.01) {
                for (int month = 0; month < 12; month++) {

                }
            }
        }
    }
}
