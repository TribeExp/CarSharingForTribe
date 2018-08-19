package com.basakdm.excartest.controller;

import com.basakdm.excartest.dao.NotificationsRepositoryDAO;
import com.basakdm.excartest.dto.NotificationsDTO;
import com.basakdm.excartest.entity.NotificationsEntity;
import com.basakdm.excartest.service.NotificationsService;
import com.basakdm.excartest.utils.converters.ConverterNotifications;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/notifications")
@Slf4j
public class NotificationsController {

    @Autowired
    private NotificationsService notificationsService;
    @Autowired
    private NotificationsRepositoryDAO notificationsRepositoryDAO;

    /**
     * Get all Notifications.
     * @return collection of NotificationsEntity.
     */
    @GetMapping("/all")
    public Collection<NotificationsDTO> findAll(){
        log.info("(/notifications/all), findAll()");
        return notificationsService.findAll().stream()
                .map(ConverterNotifications::mapNotifyUser)
                .collect(Collectors.toList());
    }

    /**
     * Find notifications by id
     * @param id notification unique identifier.
     * @return Optional with notifications, if notifications was founded. Empty optional in opposite case.
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<NotificationsDTO> findCarById(@PathVariable @Positive Long id){
        log.info("(/notifications/{id}), findCarById()");
        return notificationsService.findById(id)
                .map(ConverterNotifications::mapNotifyUser)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Create notification.
     * @param notificationsEntity params for create a new notification.
     * @return Created notification with id.
     */
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody NotificationsEntity notificationsEntity){
        log.info("(/notifications/create), create()");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ConverterNotifications.mapNotifyUser(notificationsService.create(notificationsEntity)));
    }

    /**
     * Delete notification by id.
     * @param id notification params for delete a notification.
     */
    @PostMapping("/delete/{id}")
    public void delete(@PathVariable @Positive Long id){
        log.info("(/notifications/delete), delete()");
        notificationsService.delete(id);
    }

    /**
     * Update notification by id.
     * @param notificationsEntity notification params for update a notifications.
     */
    @PostMapping ("/update")
    public void update(@RequestBody NotificationsEntity notificationsEntity){
        log.info("(/notifications/update), update()");
        notificationsService.update(notificationsEntity);
    }

    /**
     * Get text notify by id.
     * @param notifyId notification params for find a text.
     * @return  String.
     */
    @GetMapping(value = "/getTextNotifyById/{notifyId}")
    public String getTextNotifyById(@PathVariable @Positive Long notifyId){
        log.info("(/notifications/getTextNotifyById/{notifyId}), getTextNotifyById()");
        return notificationsService.findById(notifyId).get().getTextNotify();
    }

    /**
     * Get the ID of the person who sent this message.
     * @param notifyId notification params for find a FromWhomId.
     * @return  Long.
     */
    @GetMapping(value = "/getFromWhomIdById/{notifyId}")
    public Long getFromWhomIdById(@PathVariable @Positive Long notifyId){
        log.info("(/notifications/getFromWhomIdById/{notifyId}), getFromWhomIdById()");
        return notificationsService.findById(notifyId).get().getFromWhomId();
    }

    /**
     * Get the id of the person who received the message.
     * @param notifyId notification params for find a toWhomId.
     * @return  Long.
     */
    @GetMapping(value = "/getToWhomIdById/{notifyId}")
    public Long getToWhomIdById(@PathVariable @Positive Long notifyId){
        log.info("(/notifications/getToWhomIdById/{notifyId}), getToWhomIdById()");
        return notificationsService.findById(notifyId).get().getToWhomId();
    }

    /**
     * Get an order object by ID, from which you can then take any field.
     * @param notifyId notification params for find a order.
     * @return  Long.
     */
    @GetMapping(value = "/getOrderIdById/{notifyId}")
    public Long getOrderIdById(@PathVariable @Positive Long notifyId){
        log.info("(/notifications/getOrderIdById/{notifyId}), getOrderIdById()");
        return notificationsService.findById(notifyId).get().getOrderId();
    }
}
