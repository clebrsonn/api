package br.com.hyteck.api;

import br.com.hyteck.api.dto.SearchOptions;
import br.com.hyteck.api.enums.TypeCategory;
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
import java.util.HashMap;
import java.util.stream.Collectors;

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
    void test1() {

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
    void test2() {

        var technologies = technologyService.findAll();

        var df = DataFrame.of(technologies, Technology.class);//new JSON().read(tecJson);
        NominalScale nominalScale = df.stringVector("nameTec").nominal();


        double[][] X = df.select("txData", "rangeM").toArray();
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

            predicao = knn.predict(new double[]{0.1, 10});
            System.out.println("Zigbee Global, Wibree, Bluetooth Class 2: "
                    + nominalScale.level(predicao));

            predicao = knn.predict(new double[]{0.24, 10});
            System.out.println("Zigbee Global: " + nominalScale.level(predicao));

            predicao = knn.predict(new double[]{1, 9});
            System.out.println("Wibree/Bluetooth Class 2: " + nominalScale.level(predicao));

            predicao = knn.predict(new double[]{1, 8});
            System.out.println(" Wibree, Bluetooth Classe 2: " + nominalScale.level(predicao));

            predicao = knn.predict(new double[]{2, 8});
            System.out.println("Bluetooth Class 2: " + nominalScale.level(predicao));

            predicao = knn.predict(new double[]{0.5, 8});
            System.out.println("Wibree, Bluetooth Classe 2: " + nominalScale.level(predicao));

            System.out.println("--------------------TECHNOLOGIES-------------------------------------");

        }

    }

    @Test
    void test3(){

        SearchOptions searchOptions= new SearchOptions();
        var options = new HashMap<TypeCategory, Double>();

        options.put(TypeCategory.RANGE, 50d);
        options.put(TypeCategory.TX_DATA, 2d);
        searchOptions.setOptions(options);
        System.out.println("Bluetooth Smart (BLE) 1: "  + technologyService.searchTec(searchOptions).stream().
    map(Technology::getNameTec).collect(Collectors.joining(", ")));

        options.put(TypeCategory.RANGE, 100d);
        options.put(TypeCategory.TX_DATA, 3d);
        searchOptions.setOptions(options);
        System.out.println("Bluetooth Class 1: "  + technologyService.searchTec(searchOptions).stream().
    map(Technology::getNameTec).collect(Collectors.joining(", ")));


        options.put(TypeCategory.RANGE, 100d);
        options.put(TypeCategory.TX_DATA, 54d);
        searchOptions.setOptions(options);
        System.out.println("WIFI 2.4/5 Ghz: "  + technologyService.searchTec(searchOptions).stream().
    map(Technology::getNameTec).collect(Collectors.joining(", ")));


        options.put(TypeCategory.RANGE, 99d);
        options.put(TypeCategory.TX_DATA, 54d);
        searchOptions.setOptions(options);
        System.out.println("WIFI 2.4/5 Ghz: "  + technologyService.searchTec(searchOptions).stream().
    map(Technology::getNameTec).collect(Collectors.joining(", ")));

        options.put(TypeCategory.RANGE, 100d);
        options.put(TypeCategory.TX_DATA, 1d);
        searchOptions.setOptions(options);
        System.out.println("Bluetooth Class 1: " + technologyService.searchTec(searchOptions).stream().
    map(Technology::getNameTec).collect(Collectors.joining(", ")));

        options.put(TypeCategory.RANGE, 10d);
        options.put(TypeCategory.TX_DATA, 0.1);
        searchOptions.setOptions(options);
        System.out.println("Zigbee Global, Wibree, Bluetooth Class 2: "
                + technologyService.searchTec(searchOptions).stream().
    map(Technology::getNameTec).collect(Collectors.joining(", ")));

        options.put(TypeCategory.RANGE, 10d);
        options.put(TypeCategory.TX_DATA, 0.24);
        searchOptions.setOptions(options);
        System.out.println("Zigbee Global: " + technologyService.searchTec(searchOptions).stream().
    map(Technology::getNameTec).collect(Collectors.joining(", ")));

        options.put(TypeCategory.RANGE, 9d);
        options.put(TypeCategory.TX_DATA, 1d);
        searchOptions.setOptions(options);
        System.out.println("Wibree/Bluetooth Class 2: " + technologyService.searchTec(searchOptions).stream().
    map(Technology::getNameTec).collect(Collectors.joining(", ")));

        options.put(TypeCategory.RANGE, 8d);
        options.put(TypeCategory.TX_DATA, 1d);
        searchOptions.setOptions(options);
        System.out.println("Bluetooth Class 3: "  + technologyService.searchTec(searchOptions).stream().
    map(Technology::getNameTec).collect(Collectors.joining(", ")));

        options.put(TypeCategory.RANGE, 8d);
        options.put(TypeCategory.TX_DATA, 2d);
        searchOptions.setOptions(options);
        System.out.println("Bluetooth Class 2: " + technologyService.searchTec(searchOptions).stream().
                map(Technology::getNameTec).collect(Collectors.joining(", ")));

        options.put(TypeCategory.RANGE, 8d);
        options.put(TypeCategory.TX_DATA, 0.5);
        searchOptions.setOptions(options);
        System.out.println("Bluetooth Smart (BLE) 2/Bluetooth Class 3: " + technologyService.searchTec(searchOptions).stream().
    map(Technology::getNameTec).collect(Collectors.joining(", ")));

    }

}
