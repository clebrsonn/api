package br.com.hyteck.api;

import br.com.hyteck.api.record.Technology;
import br.com.hyteck.api.repository.NormalizedTechnologyRepository;
import br.com.hyteck.api.repository.TechnologyRepository;
import br.com.hyteck.api.service.DistanceService;
import br.com.hyteck.api.service.TechnologyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import smile.classification.KNN;
import smile.data.DataFrame;
import smile.data.measure.NominalScale;
import smile.neighbor.KDTree;
import smile.neighbor.KNNSearch;

import java.util.ArrayList;

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
        var tecs = technologyService.findAll();

        tecs.forEach(technology -> {
            var techologies = new ArrayList<Technology>();
            for (double i = 0; i < technology.getTx_data(); i = i + 0.01) {
                var tec = new Technology();
                BeanUtils.copyProperties(technology, tec, "id", "tx_data", "categories", "statisticalTechnologies");
                tec.setTx_data(i);
//                tec.setRange_m(technology.getRange_m());
//                tec.setBandwidth(technology.getBandwidth());
//                tec.setConsumption(tec.getConsumption());
//                tec.setNameTec(technology.getNameTec());
//                tec.setIeee(technology.getIeee());
                if (i <= technology.getTx_data() / 4) {
                    tec.setClassification("fraca");
                } else if (i <= technology.getTx_data() / 3) {
                    tec.setClassification("regular");
                } else if (i <= technology.getTx_data() / 2) {
                    tec.setClassification("bom");
                }
                //techologies.add(tec);

            }
//            technologyService.saveAll(techologies);

            for (double i = 0; i < technology.getRange_m(); i = i + 0.5) {
                var tec = new Technology();
                BeanUtils.copyProperties(technology, tec, "id", "range_m", "categories", "statisticalTechnologies");
                tec.setTx_data(i);
//                tec.setRange_m(technology.getRange_m());
//                tec.setBandwidth(technology.getBandwidth());
//                tec.setConsumption(tec.getConsumption());
//                tec.setNameTec(technology.getNameTec());
//                tec.setIeee(technology.getIeee());
                if (i <= technology.getRange_m() / 4) {
                    tec.setClassification("fraca");
                } else if (i <= technology.getRange_m() / 3) {
                    tec.setClassification("regular");
                } else if (i <= technology.getRange_m() / 2) {
                    tec.setClassification("bom");
                }
                techologies.add(tec);

            }
            for (double i = 0; i < technology.getConsumption(); i = i + 0.5) {
                var tec = new Technology();
                BeanUtils.copyProperties(technology, tec, "id", "consumption", "statisticalTechnologies");
                tec.setTx_data(i);
                if (i <= technology.getConsumption() / 4) {
                    tec.setClassification("bom");
                } else if (i <= technology.getConsumption() / 3) {
                    tec.setClassification("regular");
                } else if (i <= technology.getConsumption() / 2) {
                    tec.setClassification("fraca");
                }
                techologies.add(tec);

            }
            technologyRepository.saveAll(techologies);
        });


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

//          var statist = statisticalTechnologiesRepository.findAll();
//
//       df = DataFrame.of(statist, StatisticalTechnologies.class);//new JSON().read(tecJson);
//       nominalScale = df.stringVector("id").nominal();
//
//        X = df.select("minMaxTx","minMaxRng").toArray();
//        y = df.stringVector("id").factorize(nominalScale).toIntArray();
//    //    search = new CoverTree<>(X, new EuclideanDistance());
//       // var knn = new KNN<>(search, y, k);
//
//
//
//        double tx = (2.0 - statist.get(0).getMinTx()) / (statist.get(0).getMaxTx() - statist.get(0).getMinTx());
//        double rng = (50.0 - statist.get(0).getMinTx()) / (statist.get(0).getMaxTx() - statist.get(0).getMinTx());
//        System.out.println("--------------------StatisticalTechnologies-------------------------------------");
//
//        for (int i = 1; i < statist.size(); i++) {
//            knn =  KNN.fit(X,y, i);
//            int predicao = knn.predict(new double[]{tx, rng});
//            System.out.println(nominalScale.level(predicao));
//        }
//        System.out.println("--------------------StatisticalTechnologies2-------------------------------------");
//
//        statist = statisticalTechnologiesRepository.findAll();
//
//        df = DataFrame.of(statist, StatisticalTechnologies.class);//new JSON().read(tecJson);
//        nominalScale = df.stringVector("id").nominal();
//
//
//        tx = ((50.0- statist.get(0).getMeanTx())/statist.get(0).getSdTx());
//        rng = ((50.0- statist.get(0).getMeanRng())/statist.get(0).getSdRng());
//        X = df.select("ZScoreTx", "ZScoreRng").toArray();
//
//        for (int i = 1; i < statist.size(); i++) {
//            knn =  KNN.fit(X,y, i);
//            int predicao = knn.predict(new double[]{tx, rng});
//            System.out.println(nominalScale.level(predicao));
//        }
//
//
// //       int predicao = knn.predict(new double[]{tx, rng});
// //       assertEquals("Bluetooth Smart (BLE) 1", nominalScale.level(predicao));
//
//        SearchOptions searchOptions= new SearchOptions();
//        Map<String, Double> op = new HashMap<>();
//        op.put("txData", 5.0);
//        op.put("range_m", 100.0);
//        searchOptions.setOptions(op);
//
////        technologyService.calculateMedia();
//
//        var tecs = normalizedTechnologyRepository.findAll(SearchSpecification.normal(searchOptions));
//
//        final var variance = Math.sqrt(searchOptions.options.values().stream()
//                .map(aDouble -> Math.pow(aDouble, 2)).mapToDouble(aDouble2 -> aDouble2).sum());
//
//        List<Double> sumVetor = searchOptions.options.values().stream()
//                .map(aDouble -> aDouble / variance).collect(Collectors.toList());
//        double[] d = new double[sumVetor.size()];
//
//        for (int i = 0; i < sumVetor.size(); i++) {
//            d[i]=sumVetor.get(i);
//        }
//        System.out.println("--------------------normalizedTechnologyRepository-------------------------------------");
//
//        for (int i = 1; i < tecs.size(); i++) {
//            Classifier classifier = new Classifier(tecs, i);
//            var predicao = classifier.getKnn().predict(d);
//            System.out.println(classifier.getNominalScale().level(predicao));
//        }


//        predicao = classifier.getKnn().predict(d);
//        assertEquals("Bluetooth Smart (BLE) 1",    classifier.getNominalScale().level(predicao));

    }


}
