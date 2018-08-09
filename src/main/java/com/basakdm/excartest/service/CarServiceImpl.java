package com.basakdm.excartest.service;

import com.basakdm.excartest.dao.CarRepositoryDAO;
import com.basakdm.excartest.entity.CarEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepositoryDAO carRepositoryDAO;

    @Override
    public List<CarEntity> findAll() {
        return carRepositoryDAO.findAll();
    }

/*
    @Autowired
    private CarRepositoryDAO carRepository;

    @Override
    @Transactional(readOnly = true)
    public Collection<CarDTO> findAll() {
        return carRepository.findAll().stream().map(EntityMapperUtills::mapCar).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CarDTO> getCarById(Long id) {
        return carRepository.findById(id).map(EntityMapperUtills::mapCar);
    }

    //todo: make it in future
    @Override
    public CarDTO createCar(CarDTO carDto) {
        return null;
    }
*/

}
