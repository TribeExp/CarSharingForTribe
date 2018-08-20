package com.admin_team.carsharing.dao;

import com.admin_team.carsharing.entity.CarEntity;
import com.admin_team.carsharing.enum_ent.car_enum.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface CarRepositoryDAO extends JpaRepository<CarEntity, Long> {

    Collection<CarEntity> findAllByIsActivatedFalse();
    Collection<CarEntity> findAllByIsActivatedTrue();
    Collection<CarEntity> findAllByTransmissionType(Transmission transmission);
    Collection<CarEntity> findAllByCarBody(CarBody carBody);
    Collection<CarEntity> findAllByDriveGear(DriveGear driveGear);
    Collection<CarEntity> findAllByEngineType(TypeEngine typeEngine);
    Collection<CarEntity> findAllByFuelType(TypeFuel typeFuel);
    List<CarEntity> findAll(Specification<CarEntity> spec);

}