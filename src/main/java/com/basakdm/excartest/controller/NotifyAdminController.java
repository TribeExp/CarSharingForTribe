package com.basakdm.excartest.controller;

import com.basakdm.excartest.dto.NotifyAdminDTO;
import com.basakdm.excartest.entity.NotifyAdmin;
import com.basakdm.excartest.service.NotifyAdminService;
import com.basakdm.excartest.utils.ConvertNotifyAdmin;
import com.basakdm.excartest.utils.ConvertNotifyBoss;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.Collection;

@RestController
@RequestMapping("/notifyAdmin")
public class NotifyAdminController {

    @Autowired
    private NotifyAdminService notifyAdminService;

    @GetMapping("/all")
    Collection<NotifyAdmin> findAll(){
        return notifyAdminService.findAll();
    }

    @GetMapping(value = "/{notifyId}")
    public ResponseEntity<NotifyAdminDTO> findNotifyAdminById(@PathVariable @Positive Long notifyId){
        return notifyAdminService.findById(notifyId)
                .map(ConvertNotifyAdmin::mapNotifyAdmin)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody NotifyAdmin notifyAdmin){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ConvertNotifyAdmin.mapNotifyAdmin(notifyAdminService.create(notifyAdmin)));
    }

    @GetMapping(value = "/delete/{id}")
    public void delete(@PathVariable @Positive Long id){
        notifyAdminService.delete(id);
    }

}
