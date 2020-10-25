package br.com.hyteck.api;

import br.com.hyteck.api.record.Technology;
import br.com.hyteck.api.repository.TechnologyRepository;
import br.com.hyteck.api.service.Classifier;
import br.com.hyteck.api.service.DistanceService;
import br.com.hyteck.api.service.TechnologyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import smile.classification.KNN;
import smile.data.DataFrame;
import smile.data.measure.NominalScale;
import smile.neighbor.KDTree;
import smile.neighbor.KNNSearch;

import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ApiDataSourceApplicationTests {

    @Autowired
    private TechnologyService technologyService;

    @Test
    void contextLoads() {
    }

    @Test
    void testWithSmilesKNN() {

        var technologies = technologyService.findAll();

        var classifier = new Classifier(technologies, 1);

//        var df = DataFrame.of(technologies, Technology.class);//new JSON().read(tecJson);
//        NominalScale nominalScale = df.stringVector("nameTec").nominal();
//
//
//        double[][] X = df.select("txData", "rangeM").toArray();
//        int[] y = df.stringVector("nameTec").factorize(nominalScale).toIntArray();

//        var knn = KNN.fit(X, y, 1, new DistanceService());

        int predicao = classifier.getKnn().predict(new double[]{2, 50});
        assertEquals("BLE Classe 1", classifier.getNominalScale().level(predicao));

        predicao = classifier.getKnn().predict(new double[]{3, 100});
        assertEquals("Bluetooth Classe 1", classifier.getNominalScale().level(predicao));

        predicao = classifier.getKnn().predict(new double[]{54, 100});
        System.out.println("WIFI 2.4/5 Ghz: " + classifier.getNominalScale().level(predicao));

        predicao = classifier.getKnn().predict(new double[]{54, 99});
        System.out.println("WIFI 2.4/5 Ghz: " + classifier.getNominalScale().level(predicao));

        predicao = classifier.getKnn().predict(new double[]{1, 100});
        assertEquals("Bluetooth Classe 1", classifier.getNominalScale().level(predicao));

        predicao = classifier.getKnn().predict(new double[]{0.1, 10});
        assertEquals("Zigbee Global", classifier.getNominalScale().level(predicao));

        predicao = classifier.getKnn().predict(new double[]{0.24, 10});
        assertEquals("Zigbee Global", classifier.getNominalScale().level(predicao));

        predicao = classifier.getKnn().predict(new double[]{1, 9});
        assertEquals("Wibree", classifier.getNominalScale().level(predicao));

        predicao = classifier.getKnn().predict(new double[]{1, 8});
        assertEquals("Wibree", classifier.getNominalScale().level(predicao));

        predicao = classifier.getKnn().predict(new double[]{2, 8});
        assertEquals("Bluetooth Classe 2", classifier.getNominalScale().level(predicao));

        classifier = new Classifier(technologies, 2);

        predicao = classifier.getKnn().predict(new double[]{0.5, 8});
            assertEquals("Wibree", classifier.getNominalScale().level(predicao));

    }

    @Test
    void test3() {

        var techs = technologyService.searchTec(50, 2).stream().
                map(Technology::getNameTec).collect(Collectors.toList());
        assertEquals(1L, techs.stream().filter(s -> s.equals("BLE Classe 1")).count());
    }


    @Test
    void test4() {
        var techs = (technologyService.searchTec(100, 3).stream().
                map(Technology::getNameTec).collect(Collectors.toList()));
        assertEquals(1L, techs.stream().filter(s -> s.equals("Bluetooth Classe 1")).count());
    }

    @Test
    void test5() {
        var techs = technologyService.searchTec(100, 54).stream().
                map(Technology::getNameTec).collect(Collectors.toList());
        assertEquals(1L, techs.stream().filter(s -> s.equals("WIFI 2.4Ghz")).count());

    }

    @Test
    void test6() {

        var techs = (technologyService.searchTec(99, 54).stream().
                map(Technology::getNameTec).collect(Collectors.toList()));
        assertEquals(1L, techs.stream().filter(s -> s.equals("WIFI 2.4Ghz")).count());

    }

    @Test
    void test11() {

        var techs = (technologyService.searchTec(100, 1).stream().
                map(Technology::getNameTec).collect(Collectors.toList()));
        assertEquals(1L, techs.stream().filter(s -> s.equals("Bluetooth Classe 1")).count());
    }

    @Test
    void test12() {

        //"Zigbee Global, Wibree, Bluetooth Class 2: "
        var techs = (technologyService.searchTec(10, 0.1).stream().
                map(Technology::getNameTec).collect(Collectors.toList()));
        assertEquals(1L, techs.stream().filter(s -> s.equals("Zigbee Global")).count());
    }

    @Test
    void test13() {

        var techs = (technologyService.searchTec(10, 0.24).stream().
                map(Technology::getNameTec).collect(Collectors.toList()));
        assertEquals(1L, techs.stream().filter(s -> s.equals("Zigbee Global")).count());
    }

    @Test
    void test14() {

        //"Wibree/Bluetooth Class 2"
        var techs = (technologyService.searchTec(9, 1).stream().
                map(Technology::getNameTec).collect(Collectors.toList()));
        assertEquals(1L, techs.stream().filter(s -> s.equals("Wibree")).count());
    }

    @Test
    void test15() {

        var techs = (technologyService.searchTec(8, 1).stream().
                map(Technology::getNameTec).collect(Collectors.toList()));
        assertEquals(1L, techs.stream().filter(s -> s.equals("Wibree")).count());
    }

    @Test
    void test16() {

        var techs = (technologyService.searchTec(8, 2).stream().
                map(Technology::getNameTec).collect(Collectors.toList()));
        assertEquals(1L, techs.stream().filter(s -> s.equals("Bluetooth Classe 2")).count());
    }

    @Test
    void test17() {

        //"Bluetooth Smart (BLE) 2"
        var techs = (technologyService.searchTec(8, 0.5).stream().
                map(Technology::getNameTec).collect(Collectors.toList()));
        assertEquals(1L, techs.stream().filter(s -> s.equals("Wibree")).count());
    }

}
