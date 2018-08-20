package com.admin_team.carsharing.controller;

import com.admin_team.carsharing.dto.NotificationsDTO;
import com.admin_team.carsharing.entity.NotificationsEntity;
import com.admin_team.carsharing.service.NotificationsService;
import com.admin_team.carsharing.utils.converters.ConverterNotifications;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@Api(value = "Controller for interaction with the methods notifications", description = "The operations that can be performed with the notification table are in this controller")
@RequestMapping("/notifications")
@Slf4j
public class NotificationsController {

    @Autowired
    private NotificationsService notificationsService;

    /**
     * Get all Notifications.
     * @return collection of NotificationsEntity.
     */
    @ApiOperation(value = "Get all Notifications.", notes = "")
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
    @ApiOperation(value = "Find notifications by id.", notes = "")
    @GetMapping(value = "/{id}")
    public ResponseEntity<NotificationsDTO> findCarById(@PathVariable @Positive @ApiParam("id notification unique identifier") Long id){
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
    @ApiOperation(value = "Create notification.", notes = "")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody  @ApiParam("notificationsEntity params for create a new notification") NotificationsEntity notificationsEntity){
        log.info("(/notifications/create), create()");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ConverterNotifications.mapNotifyUser(notificationsService.create(notificationsEntity)));
    }

    /**
     * Delete notification by id.
     * @param id notification params for delete a notification.
     * @return {@link ResponseEntity}.
     */
    @ApiOperation(value = "Delete notification by id.", notes = "")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable @Positive @ApiParam("id notification params for delete a notification") Long id){
        log.info("(/notifications/delete), delete()");
        notificationsService.delete(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Update notification by id.
     * @param notificationsEntity notification params for update a notifications.
     * @return {@link ResponseEntity}.
     */
    @ApiOperation(value = "Update notification by id.", notes = "")
    @PostMapping ("/update")
    public ResponseEntity update(@RequestBody @ApiParam("notificationsEntity notification params for update a notifications") NotificationsEntity notificationsEntity){
        log.info("(/notifications/update), update()");
        notificationsService.update(notificationsEntity);
        return ResponseEntity.ok().build();
    }

    /**
     * Get text notify by id.
     * @param notifyId notification params for find a text.
     * @return {@link ResponseEntity}.
     */
    @ApiOperation(value = "Get text notify by id.", notes = "")
    @GetMapping(value = "/getTextNotifyById/{notifyId}")
    public ResponseEntity getTextNotifyById(@PathVariable @Positive @ApiParam("notifyId notification params for find a text") Long notifyId){
        log.info("(/notifications/getTextNotifyById/{notifyId}), getTextNotifyById()");
        return notificationsService.findById(notifyId).
                map(n -> ResponseEntity.ok(n.getTextNotify()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Get the ID of the person who sent this message.
     * @param notifyId notification params for find a FromWhomId.
     * @return {@link ResponseEntity}.
     */
    @GetMapping(value = "/getFromWhomIdById/{notifyId}")
    public ResponseEntity getFromWhomIdById(@PathVariable @Positive Long notifyId){
        log.info("(/notifications/getFromWhomIdById/{notifyId}), getFromWhomIdById()");
        return notificationsService.findById(notifyId).
                map(n -> ResponseEntity.ok(n.getFromWhomId()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Get the id of the person who received the message.
     * @param notifyId notification params for find a toWhomId.
     * @return {@link ResponseEntity}.
     */
    @ApiOperation(value = "Get the ID of the person who sent this message.", notes = "")
    @GetMapping(value = "/getToWhomIdById/{notifyId}")
    public ResponseEntity getToWhomIdById(@PathVariable @Positive @ApiParam("notifyId notification params for find a FromWhomId") Long notifyId){
        log.info("(/notifications/getToWhomIdById/{notifyId}), getToWhomIdById()");
        return notificationsService.findById(notifyId).
                map(n -> ResponseEntity.ok(n.getToWhomId()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Get an order object by ID, from which you can then take any field.
     * @param notifyId notification params for find a order.
     * @return {@link ResponseEntity}.
     */
    @ApiOperation(value = "Get an order object by ID, from which you can then take any field.", notes = "")
    @GetMapping(value = "/getOrderIdById/{notifyId}")
    public ResponseEntity getOrderIdById(@PathVariable @Positive @ApiParam("notifyId notification params for find a order") Long notifyId){
        log.info("(/notifications/getOrderIdById/{notifyId}), getOrderIdById()");
        return notificationsService.findById(notifyId).
                map(n -> ResponseEntity.ok(n.getOrderId()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}