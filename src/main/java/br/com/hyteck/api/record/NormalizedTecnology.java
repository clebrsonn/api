//package br.com.hyteck.api.record;
//
//import lombok.Getter;
//import lombok.Setter;
//import org.jetbrains.annotations.NotNull;
//
//import javax.persistence.*;
//
//@Entity
//@Table
//@Getter
//@Setter
//public class NormalizedTecnology {
//
//    @OneToOne
//    @JoinColumn(name = "id")
//    @MapsId
//    private Tecnology tecnology;
//
//    @Id
//    private String id;
//
//    private double calcRangeM;
//
//    private double calcBandwidth;
//
//    private double calcConsumption;
//
//    public NormalizedTecnology() {
//
//    }
//
//
//    public NormalizedTecnology(@NotNull Tecnology tecnology) {
//        this.tecnology = tecnology;
//        this.calcRangeM = tecnology.getMediaRange_m();
//        this.calcBandwidth = tecnology.getMediaBandwidth();
//        this.calcConsumption = tecnology.getMediaConsumption();
//    }
//}