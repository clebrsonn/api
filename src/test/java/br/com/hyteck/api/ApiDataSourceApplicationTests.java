package br.com.hyteck.api;

import br.com.hyteck.api.record.Technology;
import br.com.hyteck.api.repository.NormalizedTechnologyRepository;
import br.com.hyteck.api.repository.TechnologyRepository;
import br.com.hyteck.api.service.DistanceService;
import br.com.hyteck.api.service.TechnologyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import smile.base.cart.SplitRule;
import smile.classification.DecisionTree;
import smile.classification.KNN;
import smile.classification.RandomForest;
import smile.data.DataFrame;
import smile.data.formula.Formula;
import smile.data.measure.NominalScale;
import smile.neighbor.KDTree;
import smile.neighbor.KNNSearch;
import smile.validation.Accuracy;
import smile.validation.ConfusionMatrix;
import smile.validation.CrossValidation;
import smile.validation.Validation;

import java.util.Arrays;

@SpringBootTest
class ApiDataSourceApplicationTests {

    @Autowired
    private TechnologyService technologyService;

    @Autowired
    private TechnologyRepository technologyRepository;


    @Autowired
    private NormalizedTechnologyRepository normalizedTechnologyRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void testBLE1() {

        var df = DataFrame.of(technologyRepository.findAll(), Technology.class);//new JSON().read(tecJson);
        NominalScale nominalScale = df.stringVector("nameTec").nominal();
//        var tree = new DecisionTree(df, );


        var x = df.select("tx_data", "range_m").toArray();
        var y = df.column("id").toIntArray();
        var cross = CrossValidation.classification(10, x, y, (var, ints) -> KNN.fit(var, ints, 3));

        df = df.select("nameTec", "tx_data", "range_m");

        var tree = DecisionTree.fit(Formula.lhs("nameTec"), df);

        var pred = Validation.test(tree, df);


        var ret = tree.predict(DataFrame.of(new double[][]{{0.1, 100.0}}, "tx_data", "range_m"));
        System.out.println(Arrays.toString(ret));

        System.out.format("Accuracy = %.2f%%%n", (100.0 * Accuracy.of(y, pred)));
        System.out.format("Confusion Matrix: %s%n", ConfusionMatrix.of(y, pred));

        var tree2 = RandomForest.fit(Formula.lhs("nameTec"), df, 16, 2,
                SplitRule.ENTROPY, 10, 10, 2, 1);
        ret = tree2.predict(DataFrame.of(new double[][]{{0.1, 100.0}}, "tx_data", "range_m"));
        System.out.println(Arrays.toString(ret));

    }

    @Test
    void testBLE2() {

        var technologies = technologyService.findAll();

        var df = DataFrame.of(technologies, Technology.class);//new JSON().read(tecJson);
        NominalScale nominalScale = df.stringVector("nameTec").nominal();


        double[][] X = df.select("tx_data", "range_m").toArray();
        int[] y = df.stringVector("nameTec").factorize(nominalScale).toIntArray();
        //int k = 2;
        KNNSearch<double[], double[]> search = new KDTree<>(X, X);
        //var knn = new KNN<>(search, y, k);
        System.out.println("--------------------TECHNOLOGIES-------------------------------------");


        for (int i = 1; i < 3; i++) {
            var knn = KNN.fit(X, y, i, new DistanceService());
            int predicao = knn.predict(new double[]{2, 50});
            System.out.println("Bluetooth Smart (BLE) 1: " + nominalScale.level(predicao));

            predicao = knn.predict(new double[]{3, 100});
            System.out.println("Bluetooth Class 1: " + nominalScale.level(predicao));

            predicao = knn.predict(new double[]{54, 100});
            System.out.println("WIFI 2.4/5 Ghz: " + nominalScale.level(predicao));

            predicao = knn.predict(new double[]{54, 99});
            System.out.println("WIFI 2.4/5 Ghz: " + nominalScale.level(predicao));

            predicao = knn.predict(new double[]{1, 100});
            System.out.println("Bluetooth Class 1: " + nominalScale.level(predicao));

            predicao = knn.predict(new double[]{0.1, 100});
            System.out.println("WirelessHART/ Narrow Band/ LoraWAN/ Bluetooth Class 1/ WIFI 2.4/5Ghz: "
                    + nominalScale.level(predicao));

            predicao = knn.predict(new double[]{0.24, 10});
            System.out.println("Zigbee Global: " + nominalScale.level(predicao));

            predicao = knn.predict(new double[]{1, 9});
            System.out.println("Wibree/Bluetooth Smart (BLE) 1.5: " + nominalScale.level(predicao));

            predicao = knn.predict(new double[]{1, 8});
            System.out.println("Bluetooth Class 3: " + nominalScale.level(predicao));

            predicao = knn.predict(new double[]{2, 8});
            System.out.println("Bluetooth Class 2: " + nominalScale.level(predicao));

            predicao = knn.predict(new double[]{0.5, 8});
            System.out.println("Bluetooth Smart (BLE) 2/Bluetooth Class 3: " + nominalScale.level(predicao));

            System.out.println("--------------------TECHNOLOGIES-------------------------------------");

        }

    }


}
