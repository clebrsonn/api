package br.com.hyteck.api.service;

import br.com.hyteck.api.enums.TypeCategory;
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

    KNN<double[]> knn;

    NominalScale nominalScale;

    private DataFrame df;

    public Classifier(List<Technology> technologies, int k) {
        this.df = DataFrame.of(technologies, Technology.class);
        this.nominalScale = df.stringVector("nameTec").nominal();

        double[][] X = df.select(TypeCategory.TX_DATA.getType(), TypeCategory.RANGE.getType()).toArray();
        int[] y = df.stringVector("nameTec").factorize(nominalScale).toIntArray();

        this.knn = KNN.fit(X, y, k, new DistanceService());
    }
}
