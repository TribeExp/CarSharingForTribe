package com.basakdm.excartest.controller;

import com.basakdm.excartest.dto.NotifyBossDTO;
import com.basakdm.excartest.entity.NotifyBoss;
import com.basakdm.excartest.service.NotifyBossService;
import com.basakdm.excartest.utils.ConvertNotifyBoss;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.Collection;

@RestController
@RequestMapping("/notifyBoss")
public class NotifyBossController {

    @Autowired
    private NotifyBossService notifyBossService;

    @GetMapping("/all")
    Collection<NotifyBoss> findAll(){
        return notifyBossService.findAll();
    }

    @GetMapping(value = "/{notifyId}")
    public ResponseEntity<NotifyBossDTO> findNotifyAdminById(@PathVariable @Positive Long notifyId){
        return notifyBossService.findById(notifyId)
                .map(ConvertNotifyBoss::mapNotifyBoss)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody NotifyBoss notifyBoss){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ConvertNotifyBoss.mapNotifyBoss(notifyBossService.create(notifyBoss)));
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable @Positive Long id){
        notifyBossService.delete(id);
    }

}

