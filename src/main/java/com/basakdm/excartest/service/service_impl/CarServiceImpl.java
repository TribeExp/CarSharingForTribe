package com.basakdm.excartest.service.service_impl;

import com.basakdm.excartest.dao.CarRepositoryDAO;
import com.basakdm.excartest.dto.CarDTO;
import com.basakdm.excartest.service.CarService;
import com.basakdm.excartest.utils.ConverterCars;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepositoryDAO carRepositoryDAO;

    @Override
    public List<CarDTO> findAll() {
        return carRepositoryDAO.findAll().stream().map(ConverterCars::mapCar).collect(Collectors.toList());
    }

    @Override
    public Optional<CarDTO> getCarById(Long id) {
        return carRepositoryDAO.findById(id).map(ConverterCars::mapCar);
    }

/*


    @Override
    public CarDTO createCar(CarDTO carDto) {
        return null;
    }
*/

}
