package br.com.hyteck.api.service;

import smile.math.distance.Distance;
import smile.math.distance.Metric;

import java.util.Arrays;

public class DistanceService implements Distance<double[]> {
    //dado 3 pontos A, B e c
    // Onde as características de A e B definem a distância
    //C está mais perto de A ou de B
    // Regras a serem levadas em consideração:
    // tx quanto maior melhor
    // dst quanto maior melhor
    // energy quanto menor melhor
    // Levando em conta os dados com valores iguais


    @Override
    //x[tx, range, consumption]
    //y[tx, range, consumption]
    public double d(double[] x, double[] y) {
        if (x.length != y.length)
            throw new IllegalArgumentException(String.format("Lists have different length: x[%d], y[%d]", x.length, y.length));

        double dist = 0.0;

        var dist0 = 0.8 * (Math.pow(x[0]- y[0],2));

        var dist1 = 0.2 *(Math.pow(x[1]- y[1],2));

        var ym = Math.pow(dist0 + dist1, 0.5);


        double d1 = x[0] -y[0];

        double d2 = x[1] -y[1];

        dist = ym;

        return dist;
    }
}
