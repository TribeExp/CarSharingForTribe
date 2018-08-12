package com.basakdm.excartest.service.service_impl;

import com.basakdm.excartest.dao.CarRepositoryDAO;
import com.basakdm.excartest.entity.CarEntity;
import com.basakdm.excartest.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepositoryDAO carRepositoryDAO;

    @Override
    public List<CarEntity> findAll() {
        return carRepositoryDAO.findAll();
    }

    @Override
    public Optional<CarEntity> findById(Long id) {
        return carRepositoryDAO.findById(id);
    }

    @Override
    public CarEntity createCar(CarEntity carEntity) {
        return carRepositoryDAO.saveAndFlush(carEntity);
    }

    @Override
    public void delete(long id) {
        carRepositoryDAO.deleteById(id);
    }





}
