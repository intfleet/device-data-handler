package com.intellifleet.repository;

import com.intellifleet.entity.InstrumentFactsEntity;
import com.intellifleet.entity.id.InstrumentFactsId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface InstrumentFactsRepository extends JpaRepository<InstrumentFactsEntity, InstrumentFactsId> {

    Optional<InstrumentFactsEntity> findTopByTxtInstrumentIdOrderByTmsInstrumentDesc(String txtInstrumentId);

    List<InstrumentFactsEntity> findByTmsInstrumentBetween(LocalDateTime start, LocalDateTime end);

    List<InstrumentFactsEntity> findByIntCustomerUid(Integer intCustomerUid);
}
