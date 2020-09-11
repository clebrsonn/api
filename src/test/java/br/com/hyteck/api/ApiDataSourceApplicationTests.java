package br.com.hyteck.api;

import br.com.hyteck.api.record.Technology;
import br.com.hyteck.api.repository.NormalizedTechnologyRepository;
import br.com.hyteck.api.service.Classifier;
import br.com.hyteck.api.service.TechnologyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import smile.data.DataFrame;
import smile.data.formula.Formula;
import smile.regression.RandomForest;
import smile.validation.CrossValidation;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ApiDataSourceApplicationTests {

    @Autowired
    private TechnologyService technologyService;

    @Autowired    private NormalizedTechnologyRepository normalizedTechnologyRepository;


    @Test
    void contextLoads() {
    }

    @Test
    void testBLE1() {
        var tecs = technologyService.findAll();
        Classifier classifier = new Classifier(tecs);
        var d = new double[]{2, 50};
        int predicao = classifier.getKnn().predict(d);
        assertEquals("Bluetooth Smart (BLE) 1",    classifier.getNominalScale().level(predicao));
//        var norm =normalizedTechnologyRepository.findAll();
        var formula = Formula.lhs("id");
        DataFrame df = DataFrame.of(tecs, Technology.class);//new JSON().read(tecJson);

        var X = df.select("tx_data", "range_m","consumption", "id");


        var prop = new java.util.Properties();
        prop.setProperty("smile.random.forest.trees", "200");
        var forest = RandomForest.fit(formula, X);
        System.out.format("OOB error rate = %.2f%%%n", (100.0 * forest.error()));



        CrossValidation.regression(2, Formula.lhs("id"), X, RandomForest::fit);


    }

    @Test
    void testBLE2() {
        var tecs = technologyService.findAll();
        Classifier classifier = new Classifier(tecs);
        var d = new double[]{3.0, 59, 4};
        int predicao = classifier.getKnn().predict(d);
        assertEquals("Bluetooth Smart (BLE) 1",    classifier.getNominalScale().level(predicao));
    }


}
