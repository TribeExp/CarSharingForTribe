package com.basakdm.excartest.controller;

import com.basakdm.excartest.dto.NotifyAdminDTO;
import com.basakdm.excartest.entity.NotifyAdmin;
import com.basakdm.excartest.service.NotifyAdminService;
import com.basakdm.excartest.utils.ConvertNotifyAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping(value = "/create")
    public NotifyAdminDTO create(NotifyAdmin notifyAdmin){
        return ConvertNotifyAdmin.mapNotifyAdmin(notifyAdminService.create(notifyAdmin));
    }

}
