package com.basakdm.excartest.dao;

import com.basakdm.excartest.entity.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface CarRepositoryDAO extends JpaRepository<CarEntity, Long> {
    //update where id equels(entity entity)
    //void findByName(String name);

    // returns a list of unactivated car
    Collection<CarEntity> findAllByIsActivatedFalse();
    // returns a list of activated car
    Collection<CarEntity> findAllByIsActivatedTrue();


    // returns a list of unfree car
    Collection<CarEntity> findAllByIsFreeFalse();
    // returns a list of free car
    Collection<CarEntity> findAllByIsFreeTrue();

}