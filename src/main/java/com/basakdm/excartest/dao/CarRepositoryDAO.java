package com.basakdm.excartest.dao;

import com.basakdm.excartest.entity.CarEntity;
import com.basakdm.excartest.enum_ent.car_enum.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface CarRepositoryDAO extends JpaRepository<CarEntity, Long> {

    // returns a list of unactivated car
    Collection<CarEntity> findAllByIsActivatedFalse();
    // returns a list of activated car
    Collection<CarEntity> findAllByIsActivatedTrue();


    // returns a list with the specified transmission
    Collection<CarEntity> findAllByTransmissionType(Transmission transmission);
    // returns a list with the specified carBody
    Collection<CarEntity> findAllByCarBody(CarBody carBody);
    // returns a list with the specified driveGear
    Collection<CarEntity> findAllByDriveGear(DriveGear driveGear);
    // returns a list with the specified typeEngine
    Collection<CarEntity> findAllByEngineType(TypeEngine typeEngine);
    // returns a list with the specified typeFuel
    Collection<CarEntity> findAllByFuelType(TypeFuel typeFuel);

    List<CarEntity> findAll(Specification<CarEntity> spec);

}