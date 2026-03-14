package com.intellifleet.entity;

import com.intellifleet.entity.id.InstrumentFactsId;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "table_instruments_facts", schema = "data_mart")
@IdClass(InstrumentFactsId.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InstrumentFactsEntity {

    @Column(name = "row_wid", insertable = false)
    private Long rowWid;

    @Id
    @Column(name = "tms_instrument", nullable = false)
    private LocalDateTime tmsInstrument;

    @Id
    @Column(name = "txt_instrument_id", nullable = false)
    private String txtInstrumentId;

    @Column(name = "int_customer_uid")
    private Integer intCustomerUid;

    @Column(name = "txt_customer_name")
    private String txtCustomerName;

    @Column(name = "txt_resources")
    private String txtResources;

    @Column(name = "num_latitude", precision = 12, scale = 8)
    private BigDecimal numLatitude;

    @Column(name = "num_longitude", precision = 12, scale = 8)
    private BigDecimal numLongitude;

    @Column(name = "txt_location_details")
    private String txtLocationDetails;

    @Column(name = "int_sys_location_id")
    private String intSysLocationId;

    @Column(name = "txt_sys_location_name")
    private String txtSysLocationName;

    @Column(name = "int_lsys_location_dist")
    private String intLsysLocationDist;

    @Column(name = "vchr_sys_location_state")
    private String vchrSysLocationState;

    @Column(name = "int_current_speed")
    private Integer intCurrentSpeed;

    @Column(name = "num_distance")
    private BigDecimal numDistance;

    @Column(name = "txt_special_string")
    private String txtSpecialString;

    @Column(name = "int_port_number")
    private Integer intPortNumber;

    @Column(name = "int_cluster_uid")
    private Integer intClusterUid;

    @Column(name = "txt_db_node_id")
    private String txtDbNodeId;

    @Column(name = "vchr_special_flag")
    private String vchrSpecialFlag;

    @Column(name = "tms_create")
    private LocalDateTime tmsCreate;

    @Column(name = "tms_update")
    private LocalDateTime tmsUpdate;

    @Column(name = "txt_active_row")
    private String txtActiveRow;

    @Column(name = "txt_note")
    private String txtNote;

    @Column(name = "tms_data")
    private LocalDate tmsData;
}