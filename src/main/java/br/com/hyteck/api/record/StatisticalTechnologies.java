package br.com.hyteck.api.record;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatisticalTechnologies {

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    @MapsId
    @JsonIgnore
    private Technology technology;

    @Id
    private Long id;

    private double count;

    private double zScoreRng;
    private double minMaxRng;
    private double sdRng;
    private double meanRng;
    private double maxRng;
    private double minRng;

    private double zScoreEng;
    private double minMaxEng;
    private double sdEng;
    private double meanEng;
    private double minEng;
    private double maxEng;

    private double zScoreTx;
    private double minMaxTx;
    private double sdTx;
    private double meanTx;
    private double minTx;
    private double maxTx;

}
