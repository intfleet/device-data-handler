package com.intellifleet.parser.dto;

import java.math.BigDecimal;

public class InstrumentPacketBuilderWrapperDTO {
    private final InstrumentPacketDTO.InstrumentPacketDTOBuilder builder =
            InstrumentPacketDTO.builder();

    private BigDecimal lat;
    private BigDecimal lon;

    public void txtInstrumentId(String v){ builder.txtInstrumentId(v); }

    public void txtResources(String v){ builder.txtResources(v); }

    public void tmsInstrument(String v){ builder.tmsInstrument(v); }

    public void numLatitude(BigDecimal v){
        lat=v;
        builder.numLatitude(v);
    }

    public void numLongitude(BigDecimal v){
        lon=v;
        builder.numLongitude(v);
    }

    public void intCurrentSpeed(Integer v){ builder.intCurrentSpeed(v); }

    public void intPortNumber(Integer v){ builder.intPortNumber(v); }

    public void txtLocationDetails(String v){ builder.txtLocationDetails(v); }

    public BigDecimal numLatitude(){ return lat; }

    public BigDecimal numLongitude(){ return lon; }

    public InstrumentPacketDTO build(){
        return builder.build();
    }
}
