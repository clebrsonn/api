package br.com.hyteck.api.repository.specification;

import br.com.hyteck.api.dto.SearchOptions;
import br.com.hyteck.api.enums.TypeCategory;
import br.com.hyteck.api.record.NormalizedTechnology;
import br.com.hyteck.api.record.StatisticalTechnologies;
import br.com.hyteck.api.record.Technology;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;


public class SearchSpecification {

//    private static final String FIELD_SEPARATOR = ".";
//    private static final String REGEX_FIELD_SPLITTER = "\\.";

    public static Specification<NormalizedTechnology> normal(SearchOptions searchOptions) {
        return (Specification<NormalizedTechnology>) (root, criteriaQuery, criteriaBuilder) -> {

            //(consumption/sqrt((consumption^2)+(tx_data ^2)+(range_m ^2)))

//            final var variance = Math.sqrt(searchOptions.options.varues().stream()
//                    .map(aDouble -> Math.pow(aDouble, 2)).mapToDouble(aDouble2 -> aDouble2).sum());
//
//
//            final var sumVetor = searchOptions.options.varues().stream().mapToDouble(varue -> varue / variance).sum();
            var options = new ArrayList<>(searchOptions.getOptions().values());
            var statistics = options
                    .stream().mapToDouble(varue -> varue).summaryStatistics();
//            var zScore = calcZScore(options, statistics);

            return criteriaBuilder.greaterThanOrEqualTo(root.get("vetor"),0);

        };
    }


    public static double calcSd(Supplier<DoubleStream> options){

        var mean = options.get().average().getAsDouble();
        var desvio = options.get().map(value -> Math.pow(value - mean, 2)).sum();

        return Math.sqrt(desvio/options.get().count());
    }

    public static List<Technology> calcStatistics(List<Technology> tecs){

        Supplier<DoubleStream> tecTx = () -> tecs.stream().mapToDouble(Technology::getTx_data);
        var summaryStatisticsTx = tecTx.get().summaryStatistics();
        var txSd = SearchSpecification.calcSd(tecTx);


        Supplier<DoubleStream> tecRange = ()-> tecs.stream().mapToDouble(Technology::getRange_m);
        var summaryStatisticsRg = tecRange.get().summaryStatistics();
        var rgSd = SearchSpecification.calcSd(tecRange);

        Supplier<DoubleStream> tecConsumption = ()-> tecs.stream().mapToDouble(Technology::getConsumption);
        var summaryStatisticsCm = tecConsumption.get().summaryStatistics();
        var cmSd = SearchSpecification.calcSd(tecConsumption);

        for (Technology tec : tecs) {
            var statistical = StatisticalTechnologies.builder()
                    .count(tecTx.get().count())


                    .technology(tec)
                    .zScoreTx((tec.getTx_data()- summaryStatisticsTx.getAverage())/txSd)
                    .minMaxTx((tec.getTx_data()-summaryStatisticsTx.getMin())/(summaryStatisticsTx.getMax()-summaryStatisticsTx.getMin()))
                    .meanTx(summaryStatisticsTx.getAverage())
                    .maxTx(summaryStatisticsTx.getMax())
                    .minTx(summaryStatisticsTx.getMin())
                    .sdTx(txSd)

                    .zScoreEng((tec.getConsumption()- summaryStatisticsCm.getAverage())/cmSd)
                    .minMaxEng((tec.getConsumption()-summaryStatisticsCm.getMin())/(summaryStatisticsCm.getMax()-summaryStatisticsCm.getMin()))
                    .meanEng(summaryStatisticsCm.getAverage())
                    .maxEng(summaryStatisticsCm.getMax())
                    .minEng(summaryStatisticsCm.getMin())
                    .sdEng(cmSd)

                    .zScoreRng((tec.getRange_m()- summaryStatisticsRg.getAverage())/rgSd)
                    .minMaxRng((tec.getRange_m()-summaryStatisticsRg.getMin())/(summaryStatisticsRg.getMax()-summaryStatisticsRg.getMin()))
                    .meanRng(summaryStatisticsRg.getAverage())
                    .maxRng(summaryStatisticsRg.getMax())
                    .minRng(summaryStatisticsRg.getMin())
                    .sdRng(rgSd)

                    .build();

            tec.setStatisticalTechnologies(statistical);
        }


        return tecs;
    }

//    public static Specification<Technology> filterWithOptions(SearchOptions params) {
//        return (root, query, criteriaBuilder) -> {
//            try {
//                List<Predicate> predicates = new ArrayList<>();
//                for (String field : params.options.keySet()) {
//                    if (field.contains(FIELD_SEPARATOR)) {
//                        filterInDepth(params.options, root, criteriaBuilder, predicates, field);
//                    } else {
//                        if (Technology.class.getDeclaredField(field) != null) {
//                            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(field), params.options.get(field)));
//                        }
//                    }
//                }
//                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
//            } catch (NoSuchFieldException e) {
//                e.printStackTrace();
//            }
//            return null;
//        };
//    }


//    private static void filterInDepth(Map<String, Double> params, Root<Technology> root, CriteriaBuilder criteriaBuilder,
//                                      List<Predicate> predicates, String field) throws NoSuchFieldException {
//        String[] compositeField = field.split(REGEX_FIELD_SPLITTER);
//        if (compositeField.length == 2) {
//            if (Collection.class.isAssignableFrom(Technology.class.getDeclaredField(compositeField[0]).getType())) {
//                Join<Object, Object> join = root.join(compositeField[0]);
//                predicates.add(criteriaBuilder.greaterThanOrEqualTo(join.get(compositeField[1]), params.get(field)));
//            }
//        } else if (Technology.class.getDeclaredField(compositeField[0]).getType().getDeclaredField(compositeField[1]) != null) {
//            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(compositeField[0]).get(compositeField[1]), params.get(field)));
//        }
//    }

}
