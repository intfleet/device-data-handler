package com.intellifleet.parser.dto;

public class InstrumentPacketBuilderWrapperDTO {
    private final InstrumentPacketDTO.InstrumentPacketDTOBuilder builder =
            InstrumentPacketDTO.builder();

    private Double lat;
    private Double lon;

    public void txtInstrumentId(String v){ builder.txtInstrumentId(v); }

    public void txtResources(String v){ builder.txtResources(v); }

    public void tmsInstrument(String v){ builder.tmsInstrument(v); }

    public void numLatitude(Double v){
        lat=v;
        builder.numLatitude(v);
    }

    public void numLongitude(Double v){
        lon=v;
        builder.numLongitude(v);
    }

    public void intCurrentSpeed(Integer v){ builder.intCurrentSpeed(v); }

    public void intPortNumber(Integer v){ builder.intPortNumber(v); }

    public void txtLocationDetails(String v){ builder.txtLocationDetails(v); }

    public Double numLatitude(){ return lat; }

    public Double numLongitude(){ return lon; }

    public InstrumentPacketDTO build(){
        return builder.build();
    }
}
