package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.*;

public class Main extends Application {

    public static List<RegionData> regions = new ArrayList<>();

    public static void main(String[] args) {
        poplateRegions();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 1200, 800));
        primaryStage.show();
    }

    private static void poplateRegions(){
        double vacStep = 0.1;

        RegionData vinnuzka = new RegionData(
                "Вінницька",
                new double[]{
                    0.242061391,
                    0.080689309,
                    0.000790112,
                    0.985496562,
                    0.98545093,
                    0.985239246,
                    0.985082081,
                    0.985418774,
                    122.9323271,
                    2.501739306,
                    84.18243156,
                    3.037230063
                },
                1.5904,
                0.000641726,
                vacStep
        );
        RegionData volunska = new RegionData(
                "Волинська",
                new double[]{
                        4.199962501,
                        0.524273026,
                        0.000898955,
                        0.940936474,
                        0.928917442,
                        0.917775837,
                        0.889835325,
                        0.87889999,
                        0.872283425,
                        0.866141798,
                        497.4776181,
                        1.625244911

                },
                1.041,
                0.00000480681,
                vacStep
        );
        RegionData dnipropetrovskya = new RegionData(
                "Дніпропетровська",
                new double[]{
                        2.305574169,
                        0.067188889,
                        0.000266114,
                        0.999855537,
                        0.999788084,
                        0.999723149,
                        0.999658877,
                        0.999596626,
                        0.999548304,
                        0.999513819,
                        23297.7609,
                        0.239328244

                },
                3.2304,
                0.000243178,
                vacStep
        );
        RegionData donezka = new RegionData(
                "Донецька",
                new double[]{
                        0.364027689,
                        0.126321633,
                        0.043633282,
                        0.00851948,
                        0.99426663,
                        0.994298724,
                        0.994351675,
                        0.994427143,
                        23.21306916,
                        10.97267949,
                        97.22017021,
                        2.395089531

                },
                4.244,
                0.000591219,
                vacStep
        );
        RegionData gutomurska = new RegionData(
                "Житомирська",
                new double[]{
                        1.058021516,
                        0.077082673,
                        0.056640183,
                        0.001403965,
                        79.64736611,
                        0.012505735,
                        0.997072369,
                        0.996669998,
                        238.6430646,
                        3.331124393,
                        237.7399805,
                        0.719991781

                },
                1.2405,
                0.001554823,
                vacStep
        );
        RegionData zakarpatska = new RegionData(
                "Закарпатська",
                new double[]{
                        2.454333099,
                        0.037258142,
                        0.006077897,
                        0.965393357,
                        0.962016255,
                        0.957365907,
                        0.951899778,
                        0.94655779,
                        0.942796754,
                        0.940092735,
                        298.7834977,
                        6.204064466

                },
                1.2588,
                0.0000175057,
                vacStep
        );
        RegionData zaporizka = new RegionData(
                "Запорізька",
                new double[]{
                        1.918113123,
                        0.048947647,
                        0.000159822,
                        163.2495156,
                        0.005670367,
                        0.968767511,
                        0.966410821,
                        0.963438583,
                        0.963134428,
                        0.960043853,
                        10777.36679,
                        16.12278039

                },
                1.7395 ,
                0.000653207,
                vacStep
        );
        RegionData ivanoFrankivska = new RegionData(
                "Ів.-Франковська",
                new double[]{
                        2.182700997,
                        0.265215706,
                        0.28242363,
                        0.001290969,
                        0.843543965,
                        0.844116491,
                        0.826395798,
                        0.81842602,
                        0.829473283,
                        0.84329967,
                        7682.679758,
                        3.677554844

                },
                1.3799  ,
                0.0000384106,
                vacStep
        );
        RegionData kuiuvska = new RegionData(
                "Київська",
                new double[]{
                        0.707064023,
                        0.109473456,
                        0.000166069,
                        1.002063039,
                        1.002055707,
                        1.00204598,
                        1.002033929,
                        1.002016462,
                        1.002007803,
                        290.6976358,
                        149.5641985,
                        0.823451452

                },
                1.7345  ,
                0.000767644,
                vacStep
        );
        RegionData kirovogradska = new RegionData(
                "Кіровоградська",
                new double[]{
                        0.586411537,
                        0.038177501,
                        0.139463129,
                        0.166574968,
                        0.009736417,
                        0.999460046,
                        0.999457776,
                        102.5969245,
                        0.009743888,
                        205.3509956,
                        433.2126371,
                        1.591614086

                },
                0.9658,
                0.001980159,
                vacStep
        );
        RegionData luganska = new RegionData(
                "Луганська",
                new double[]{
                        0.825528042,
                        0.076434755,
                        0.033307222,
                        0.022164205,
                        1.000395591,
                        1.000361737,
                        1.00031585,
                        1.000265321,
                        1.000208993,
                        1.000159309,
                        12549.17281,
                        0.765938256

                },
                2.3865,
                0.000214393,
                vacStep
        );
        RegionData lvivska = new RegionData(
                "Львівська",
                new double[]{
                        1.548806773,
                        0.122427408,
                        0.087379214,
                        0.074918479,
                        0.012265758,
                        0.973682835,
                        0.973668656,
                        0.973643232,
                        38.64428228,
                        5.837167885,
                        366.3091101,
                        0.803581608

                },
                2.534,
                0.000623554,
                vacStep
        );
        RegionData mukolaivska = new RegionData(
                "Миколаївська",
                new double[]{
                        2.437231369,
                        0.233160138,
                        0.055804456,
                        0.001128158,
                        74.9897837,
                        0.010226966,
                        0.878968903,
                        0.879036154,
                        302.1758528,
                        0.870920729,
                        160.7206023,
                        1.803258776

                },
                1.1501,
                0.000243205,
                vacStep
        );
        RegionData odeska = new RegionData(
                "Одеська",
                new double[]{
                        0.52652858,
                        0.102146837,
                        0.06799272,
                        0.001954741,
                        0.983251081,
                        0.983202431,
                        0.983093703,
                        0.983033657,
                        0.982917845,
                        0.982822801,
                        50263.75568,
                        1.799572819

                },
                2.3865,
                0.001578364,
                vacStep
        );
        RegionData poltavksa = new RegionData(
                "Полтавська",
                new double[]{
                        1.471270767,
                        0.270320283,
                        0.082981724,
                        4.068059148,
                        0.000272191,
                        1.000984653,
                        1.000971887,
                        1.000957951,
                        347.2743476,
                        0.002875531,
                        9691.332999,
                        0.836515769

                },
                1.4268,
                0.000274089,
                vacStep
        );
        RegionData rivnenska = new RegionData(
                "Рівненська",
                new double[]{
                        0.383943342,
                        0.02702511,
                        0.182136238,
                        0.001926312,
                        0.996277734,
                        0.996272768,
                        0.996270066,
                        85.87857602,
                        8.968171883,
                        1.442598707,
                        145.6685291,
                        0.947699619

                },
                1.1627,
                0.002805814,
                vacStep
        );
        RegionData sumska = new RegionData(
                "Сумська",
                new double[]{
                        0.612401525,
                        0.054999729,
                        0.017490153,
                        0.011317468,
                        180.0111817,
                        1.973368001,
                        0.496613417,
                        0.005569215,
                        442.9716482,
                        11.40409996,
                        17.5091676,
                        0.322264365

                },
                1.1045,
                0.001540237,
                vacStep
        );
        RegionData ternopilska = new RegionData(
                "Тернопільська",
                new double[]{
                        1.397212735,
                        0.100034077,
                        0.210527603,
                        0.002666716,
                        0.999972857,
                        0.999918219,
                        0.99985122,
                        0.999803285,
                        0.999772061,
                        562.352755,
                        15.51300589,
                        0.194888704

                },
                1.0592,
                0.000127498,
                vacStep
        );
        RegionData harkivska = new RegionData(
                "Харківська",
                new double[]{
                        1.213996423,
                        0.594352097,
                        0.175184966,
                        0.009540256,
                        0.922891092,
                        0.953678771,
                        1.004549747,
                        1.050748794,
                        1.081258264,
                        76.58282951,
                        3.754939131,
                        0.434775795

                },
                2.7012,
                0.00007401499,
                vacStep
        );
        RegionData hersonska = new RegionData(
                "Херсонська",
                new double[]{
                        0.217570041,
                        0.024700564,
                        0.009671945,
                        0.906977567,
                        0.911516546,
                        0.917806369,
                        0.923854641,
                        0.928917828,
                        0.935415345,
                        0.943629236,
                        42288.52274,
                        1.337209248

                },
                1.0556,
                0.000180975,
                vacStep
        );
        RegionData hmelnuzka = new RegionData(
                "Хмельницька",
                new double[]{
                        1.931232642,
                        0.246598235,
                        0.166364341,
                        0.122195693,
                        0.003263188,
                        1.005489806,
                        1.005435834,
                        1.005379377,
                        232.3426134,
                        3.651322235,
                        44.79729039,
                        0.479379993

                },
                1.2853,
                0.000322014,
                vacStep
        );
        RegionData cherkaska = new RegionData(
                "Черкаська",
                new double[]{
                       0.529955051,
                       0.169747447,
                       0.186744931,
                       0.131778694,
                       0.020614452,
                       7.939309385,
                       0.123733418,
                       0.991338913,
                       0.991366628,
                       95.31144788,
                       11.00299451,
                       0

                },
                1.2312,
                0.000697812,
                vacStep
        );
        RegionData chernivezka = new RegionData(
                "Чернівецька",
                new double[]{
                       1.595519173,
                       0.110164808,
                       0.000393961,
                       0.998823086,
                       0.998583508,
                       0.998919785,
                       0.99984467,
                       1.000769062,
                       1.001106784,
                       993.2446886,
                       12.57101538,
                       0.166458197


                },
                0.9081,
                0.000144448,
                vacStep
        );
        RegionData chernihivska = new RegionData(
                "Чернігівська",
                new double[]{
                       3.509545948,
                       0.138827963,
                       0.004948285,
                       0.004918982,
                       0.939510248,
                       0.939510066,
                       0.939509106,
                       0.939505896,
                       0.939507509,
                       1166.386533,
                       62.59996318,
                       1.388746465


                },
                1.0334,
                0.000746802,
                vacStep
        );
        RegionData kuiuv = new RegionData(
                "м.Київ",
                new double[]{
                       0.545229097,
                       0.068416975,
                       0.090911199,
                       0.500001054,
                       0.01043779,
                       0.090909092,
                       110.0000001,
                       10.0000011,
                       0.638706185,
                       9.500006674,
                       137.3693379,
                       0.210429128


                },
                2.9258,
                0.000622131,
                vacStep
        );

        regions.add(vinnuzka);
        regions.add(volunska);
        regions.add(dnipropetrovskya);
        regions.add(donezka);
        regions.add(gutomurska);
        regions.add(zakarpatska);
        regions.add(zaporizka);
        regions.add(ivanoFrankivska);
        regions.add(kuiuvska);
        regions.add(kirovogradska);
        regions.add(luganska);
        regions.add(lvivska);
        regions.add(mukolaivska);
        regions.add(odeska);
        regions.add(poltavksa);
        regions.add(rivnenska);
        regions.add(sumska);
        regions.add(ternopilska);
        regions.add(harkivska);
        regions.add(hersonska);
        regions.add(hmelnuzka);
        regions.add(cherkaska);
        regions.add(chernivezka);
        regions.add(chernihivska);
        regions.add(kuiuv);
    }
}
