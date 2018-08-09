package com.basakdm.excartest.service.service_impl;

import com.basakdm.excartest.dao.CarRepositoryDAO;
import com.basakdm.excartest.dto.CarDTO;
import com.basakdm.excartest.entity.CarEntity;
import com.basakdm.excartest.service.CarService;
import com.basakdm.excartest.utils.EntityMapperUtills;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    public static final String SEDAN = "sedan";
    public static final String NISSAN_CAR_BRAND = "Nissan";

    @Autowired
    private CarRepositoryDAO carRepositoryDAO;

    @Override
    public List<CarDTO> findAll() {
        return carRepositoryDAO.findAll().stream().map(EntityMapperUtills::mapCar).collect(Collectors.toList());
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
