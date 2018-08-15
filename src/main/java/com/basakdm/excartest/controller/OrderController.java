package com.basakdm.excartest.controller;

import com.basakdm.excartest.dao.OrderRepositoryDAO;
import com.basakdm.excartest.dto.OrderDTO;
import com.basakdm.excartest.entity.CarEntity;
import com.basakdm.excartest.entity.OrderEntity;
import com.basakdm.excartest.service.CarService;
import com.basakdm.excartest.service.OrderService;
import com.basakdm.excartest.utils.ConvertOrders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepositoryDAO orderRepositoryDAO;

    @Autowired
    private CarService carServiceImpl;

    @GetMapping("/all")
    public Collection<OrderDTO> findAll(){
        return orderService.findAll().stream()
                .map(ConvertOrders::mapOrder)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderDTO> findUserById(@PathVariable @Positive Long id){
        return orderService.findById(id)
                .map(ConvertOrders::mapOrder)
                .map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody OrderEntity orderEntity){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(orderService.create(orderEntity));
    }

    @DeleteMapping ("/delete/{id}")
    public void delete(@PathVariable @Positive Long id){
        orderService.delete(id);
    }

    @PostMapping ("/update")
    public void update(@RequestBody OrderEntity orderEntity){
        orderService.update(orderEntity);
    }

    // number of days by car
    @GetMapping(value = "/getAmountOfDaysById/{orderId}")
    public Integer getAmountOfDaysById(@PathVariable @Positive Long orderId){
        return orderService.findById(orderId).get().getAmountOfDays();
    }
    // calculate last day driving
    @GetMapping(value = "/calcDateFromMomentOfTakingCar/{orderId}")
    public Date calcDateFromMomentOfTakingCar(@PathVariable @Positive Long orderId){

        Integer amountOfDays = getAmountOfDaysById(orderId);

        Optional<OrderEntity> optionalOrderEntity = orderService.findById(orderId);
        OrderEntity orderEntity = optionalOrderEntity.get();
        Date firstDay = orderEntity.getFromWhatDate();

        Date lastDay = firstDay;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(lastDay);
        calendar.add(Calendar.DAY_OF_WEEK, amountOfDays);

        lastDay = (Date) calendar.getTime();
        return lastDay;
    }

    @GetMapping(value = "/getPriceAdd/{orderId}")
    public Long getPriceAdd(@PathVariable @Positive Long orderId){
        return orderService.findById(orderId).get().getPriceAdd();
    }
    @PostMapping ("/setPriceAdd/{orderId}/{priceAdd}")
    public void setPriceAdd(@RequestBody @PathVariable @Positive Long orderId, @PathVariable @Positive Long priceAdd){

        Optional<OrderEntity> optionalOrderEntity = orderService.findById(orderId);
        OrderEntity orderEntity = optionalOrderEntity.get();
        orderEntity.setPriceAdd(priceAdd);

        orderRepositoryDAO.saveAndFlush(orderEntity);
    }

    // looking for a price from the order, by carId
    @GetMapping(value = "/getPriceAddByIdCar/{carId}")
    public Long getPriceAddByIdCar(@PathVariable @Positive Long carId){

        Optional<OrderEntity> optionalOrderEntity = orderService.findByIdCar(carId);
        OrderEntity orderEntity = optionalOrderEntity.get();
        return orderEntity.getPriceAdd();
    }

    // on this essence you can access any cell
    @GetMapping(value = "/getOrderByIdCar/{carId}")
    public OrderEntity getOrderEntityByIdCar(@PathVariable @Positive Long carId){

        Optional<OrderEntity> optionalOrderEntity = orderService.findByIdCar(carId);
        OrderEntity orderEntity = optionalOrderEntity.get();

        return orderEntity;
    }

    // method from car controller for calculate finPrice
    @GetMapping(value = "/getCarEntityByIdCar/{carId}")
    public CarEntity getCarEntityById(@PathVariable @Positive Long carId){
        return carServiceImpl.findById(carId).get();
    }

    @GetMapping(value = "/getFinPriceByIdCar/{carId}")
    public Long getFinPriceByIdCar(@PathVariable @Positive Long carId){
        return orderService.findByIdCar(carId).get().getFinPrice();
    }
    @PostMapping ("/setFinPriceByIdCar/{carId}")
    public void setFinPriceByIdCar(@RequestBody @PathVariable @Positive Long carId){
        Long finPrice;
        if (getPriceAddByIdCar(carId) == null) {
            finPrice = getCarEntityById(carId).getPrice() * getOrderEntityByIdCar(carId).getAmountOfDays();
        } else {
            finPrice = getCarEntityById(carId).getPrice() * getOrderEntityByIdCar(carId).getAmountOfDays() + getPriceAddByIdCar(carId);
        }

        Optional<OrderEntity> optionalOrderEntity = orderService.findByIdCar(carId);
        OrderEntity orderEntity = optionalOrderEntity.get();

        orderEntity.setFinPrice(finPrice);

        orderRepositoryDAO.saveAndFlush(orderEntity);
    }





}
