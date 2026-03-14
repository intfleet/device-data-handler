package com.intellifleet.entity.id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InstrumentFactsId implements Serializable {

    private String txtInstrumentId;

    private LocalDateTime tmsInstrument;
}
