package com.intellifleet.parser.services;

import com.intellifleet.parser.config.PacketMappingConfig;
import com.intellifleet.parser.dto.InstrumentConfigDTO;
import com.intellifleet.parser.dto.InstrumentPacketBuilderWrapperDTO;
import com.intellifleet.parser.dto.InstrumentPacketDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.function.BiConsumer;

@Service
@RequiredArgsConstructor
public class GPSDataParser {

    private final PacketMappingConfig config;

    private static final Map<String, BiConsumer<InstrumentPacketBuilderWrapperDTO,String>> FIELD_SETTERS = Map.of(

            "txt_instrument_id", InstrumentPacketBuilderWrapperDTO::txtInstrumentId,
            "txt_resources", InstrumentPacketBuilderWrapperDTO::txtResources,
            "tms_instrument", InstrumentPacketBuilderWrapperDTO::tmsInstrument,

            "num_latitude",(b,v)->b.numLatitude(Double.parseDouble(v)),
            "num_longitude",(b,v)->b.numLongitude(Double.parseDouble(v)),

            "int_current_speed",(b,v)->b.intCurrentSpeed((int)Double.parseDouble(v))
    );

    public InstrumentPacketDTO parse(String raw, int port){

        HeaderResult result = detectHeader(raw);

        if(result == null)
            throw new RuntimeException("Header not recognized");

        InstrumentConfigDTO device = config.getMapping(result.header());

        InstrumentPacketBuilderWrapperDTO builder = new InstrumentPacketBuilderWrapperDTO();

        builder.intPortNumber(port);

        device.getFieldIndexes().forEach((field,index)->{

            if(index >= result.values().length)
                return;

            var setter = FIELD_SETTERS.get(field);

            if(setter != null)
                setter.accept(builder,result.values()[index]);
        });

        builder.txtLocationDetails(
                builder.numLatitude()+","+builder.numLongitude()
        );

        return builder.build();
    }

    private HeaderResult detectHeader(String raw){

        for(String header : config.getHeaders()){

            InstrumentConfigDTO device = config.getMapping(header);

            String[] parts = raw.split(device.getSep(),3);

            if(parts.length > 1 && header.equals(parts[1])){

                String[] values = raw.split(device.getSep(),-1);

                return new HeaderResult(header,values);
            }
        }

        return null;
    }

    private record HeaderResult(String header,String[] values){}

}
