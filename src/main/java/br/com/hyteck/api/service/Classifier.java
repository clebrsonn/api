package br.com.hyteck.api.service;

import br.com.hyteck.api.record.Technology;
import lombok.Getter;
import lombok.Setter;
import smile.classification.KNN;
import smile.data.DataFrame;
import smile.data.measure.NominalScale;
import smile.neighbor.CoverTree;
import smile.neighbor.KNNSearch;

import java.util.List;

@Getter
@Setter
public class Classifier {

    private KNN<double[]> knn;

    private NominalScale nominalScale;

    private DataFrame df;

    public Classifier(List<Technology> technologies, int k) {
//        this.df = DataFrame.of(technologies, Technology.class);//new JSON().read(tecJson);
//        this.nominalScale = df.stringVector("nameTec").nominal();
//
//        double[][] X = df.select("tx_data", "range_m").toArray();
//        int[] y = df.stringVector("nameTec").factorize(nominalScale).toIntArray();
//        KNNSearch<double[], double[]> search = new CoverTree<>(X, new DistanceService());
//        this.knn = new KNN<>(search, y, k);
    }
}
