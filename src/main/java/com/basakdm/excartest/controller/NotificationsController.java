package com.basakdm.excartest.controller;

import com.basakdm.excartest.dao.NotificationsRepositoryDAO;
import com.basakdm.excartest.dto.NotificationsDTO;
import com.basakdm.excartest.entity.NotificationsEntity;
import com.basakdm.excartest.service.NotificationsService;
import com.basakdm.excartest.utils.ConverterNotifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.Notification;
import javax.validation.constraints.Positive;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/notifications")
public class NotificationsController {

    @Autowired
    private NotificationsService notificationsService;
    @Autowired
    private NotificationsRepositoryDAO notificationsRepositoryDAO;

    @GetMapping("/all")
    public Collection<NotificationsDTO> findAll(){
        return notificationsService.findAll().stream()
                .map(ConverterNotifications::mapNotifyUser)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<NotificationsDTO> findCarById(@PathVariable @Positive Long id){
        return notificationsService.findById(id)
                .map(ConverterNotifications::mapNotifyUser)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody NotificationsEntity notificationsEntity){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ConverterNotifications.mapNotifyUser(notificationsService.create(notificationsEntity)));
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable @Positive Long id){
        notificationsService.delete(id);
    }

    @PostMapping ("/update")
    public void update(@RequestBody NotificationsEntity notificationsEntity){
        notificationsService.update(notificationsEntity);
    }


    @GetMapping(value = "/getTextNotifyById/{notifyId}")
    public String getTextNotifyById(@PathVariable @Positive Long notifyId){
        return notificationsService.findById(notifyId).get().getText_notify();
    }

    @GetMapping(value = "/getFromWhomIdById/{notifyId}")
    public Long getFromWhomIdById(@PathVariable @Positive Long notifyId){
        return notificationsService.findById(notifyId).get().getFrom_whom_id();
    }
    @GetMapping(value = "/getToWhomIdById/{notifyId}")
    public Long getToWhomIdById(@PathVariable @Positive Long notifyId){
        return notificationsService.findById(notifyId).get().getTo_whom_id();
    }

    @GetMapping(value = "/getOrderIdById/{notifyId}")
    public Long getOrderIdById(@PathVariable @Positive Long notifyId){
        return notificationsService.findById(notifyId).get().getOrder_id();
    }
    @PostMapping(value = "/setOrderIdById/{notifyId}/{orderId}")
    public void setOrderIdById(@RequestBody @PathVariable @Positive Long notifyId, @PathVariable @Positive Long orderId){

        Optional<NotificationsEntity> optionalNotificationsEntity = notificationsService.findById(notifyId);
        NotificationsEntity notificationsEntity = optionalNotificationsEntity.get();
        notificationsEntity.setOrder_id(orderId);

        notificationsRepositoryDAO.saveAndFlush(notificationsEntity);
    }

}
