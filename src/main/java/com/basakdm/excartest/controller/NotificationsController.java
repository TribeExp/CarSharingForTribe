package com.basakdm.excartest.controller;

import com.basakdm.excartest.dto.NotificationsDTO;
import com.basakdm.excartest.entity.NotificationsEntity;
import com.basakdm.excartest.service.NotificationsService;
import com.basakdm.excartest.utils.ConverterNotifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/notifications")
public class NotificationsController {

    @Autowired
    private NotificationsService notificationsService;

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
}
