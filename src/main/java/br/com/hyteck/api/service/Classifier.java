package br.com.hyteck.api.service;

import br.com.hyteck.api.record.Tecnology;
import lombok.Getter;
import lombok.Setter;
import smile.classification.KNN;
import smile.data.DataFrame;
import smile.data.measure.NominalScale;
import smile.math.distance.ManhattanDistance;
import smile.neighbor.CoverTree;
import smile.neighbor.KNNSearch;

import java.util.List;

@Getter
@Setter
public class Classifier {

    private KNN knn;

    private NominalScale nominalScale;

    private DataFrame df;

    public Classifier(List<Tecnology> tecnologies) {
//        CSVFormat format = CSVFormat.DEFAULT.withHeader("SepalLength", "SepalWidth", "PetalLength", "PetalWidth", "Class");
//        ObjectMapper mapper = new ObjectMapper();
//        final var tecJson = mapper.writeValueAsString(tecnologies);

        this.df = DataFrame.of(tecnologies, Tecnology.class);//new JSON().read(tecJson);
        this.nominalScale = df.stringVector("nameTec").nominal();

        double[][] X = df.select("tx_data", "range_m", "consumption").toArray();
        int[] y = df.stringVector("nameTec").factorize(this.nominalScale).toIntArray();
        int k = 2;
        KNNSearch search = new CoverTree(X, new ManhattanDistance());
        this.knn = new KNN(search, y, k);
    }
}
