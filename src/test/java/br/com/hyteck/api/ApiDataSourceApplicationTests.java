package br.com.hyteck.api;

import br.com.hyteck.api.record.NormalizedTecnology;
import br.com.hyteck.api.repository.NormalizedTecnologyRepository;
import br.com.hyteck.api.service.Classifier;
import br.com.hyteck.api.service.TecnologyService;
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
    private TecnologyService tecnologyService;

    @Autowired    private NormalizedTecnologyRepository normalizedTecnologyRepository;


    @Test
    void contextLoads() {
    }

    @Test
    void testBLE1() {
        var tecs = tecnologyService.findAll();
        Classifier classifier = new Classifier(tecs);
        var d = new double[]{2, 50, 10};
        int predicao = classifier.getKnn().predict(d);
        assertEquals("Bluetooth Smart (BLE) 1",    classifier.getNominalScale().level(predicao));
        var norm =normalizedTecnologyRepository.findAll();
        var formula = Formula.lhs("id");
        DataFrame df = DataFrame.of(norm, NormalizedTecnology.class);//new JSON().read(tecJson);

        var prop = new java.util.Properties();
        prop.setProperty("smile.random.forest.trees", "200");
        var forest = RandomForest.fit(formula, df, prop);
        System.out.format("OOB error rate = %.2f%%%n", (100.0 * forest.error()));

        CrossValidation.regression(10, Formula.lhs("nameTec"), df, RandomForest::fit);


    }

    @Test
    void testBLE2() {
        var tecs = tecnologyService.findAll();
        Classifier classifier = new Classifier(tecs);
        var d = new double[]{3.0, 59, 4};
        int predicao = classifier.getKnn().predict(d);
        assertEquals("Bluetooth Smart (BLE) 1",    classifier.getNominalScale().level(predicao));
    }


}
