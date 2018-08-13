package com.basakdm.excartest.dao;

import com.basakdm.excartest.entity.CarEntity;
import com.basakdm.excartest.enum_ent.car_enum.Transmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface CarRepositoryDAO extends JpaRepository<CarEntity, Long> {

    // returns a list of unactivated car
    Collection<CarEntity> findAllByIsActivatedFalse();
    // returns a list of activated car
    Collection<CarEntity> findAllByIsActivatedTrue();


    // returns a list of unfree car
    Collection<CarEntity> findAllByIsFreeFalse();
    // returns a list of free car
    Collection<CarEntity> findAllByIsFreeTrue();


    // returns a list with the specified transmission
    Collection<CarEntity> findAllByTransmissionType(Transmission transmission);

}