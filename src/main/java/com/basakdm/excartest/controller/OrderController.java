package com.basakdm.excartest.controller;

import com.basakdm.excartest.dao.OrderRepositoryDAO;
import com.basakdm.excartest.dto.OrderDTO;
import com.basakdm.excartest.entity.OrderEntity;
import com.basakdm.excartest.service.OrderService;
import com.basakdm.excartest.utils.ConvertOrders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.text.DateFormat;
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
    @GetMapping(value = "/getAmountOfDaysById/{userId}")
    public Integer getAmountOfDaysById(@PathVariable @Positive Long userId){
        return orderService.findById(userId).get().getAmount_of_days();
    }
    // calculate last day driving
    @GetMapping(value = "/calcDateFromMomentOfTakingCar/{orderId}")
    public Date calcDateFromMomentOfTakingCar(@PathVariable @Positive Long orderId){

        Integer amountOfDays = getAmountOfDaysById(orderId);

        Optional<OrderEntity> optionalOrderEntity = orderService.findById(orderId);
        OrderEntity orderEntity = optionalOrderEntity.get();
        Date firstDay = orderEntity.getFrom_what_date();

        Date lastDay = firstDay;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(lastDay);
        calendar.add(Calendar.DAY_OF_WEEK, amountOfDays);

        lastDay = (Date) calendar.getTime();
        return lastDay;
    }
}
