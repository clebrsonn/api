package br.com.hyteck.api.dto;

import br.com.hyteck.api.record.Technology;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TechnologyDTO {

    private String name;
    private Double consumption;
    private Double txData;
    private Double range;
    private boolean best = false;


    public TechnologyDTO(Technology technology, String classification) {
        this.name = technology.getNameTec();
        this.consumption = technology.getConsumption();
        this.txData = technology.getTxData();
        this.range = technology.getRangeM();
        if(name.equalsIgnoreCase(classification) || classification.isBlank()) {
            this.best = true;
        }

    }



}
