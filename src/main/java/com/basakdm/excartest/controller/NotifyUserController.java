package com.basakdm.excartest.controller;

import com.basakdm.excartest.dto.NotifyBossDTO;
import com.basakdm.excartest.dto.NotifyUserDTO;
import com.basakdm.excartest.entity.NotifyBoss;
import com.basakdm.excartest.entity.NotifyUser;
import com.basakdm.excartest.service.NotifyUserService;
import com.basakdm.excartest.utils.ConvertNotifyBoss;
import com.basakdm.excartest.utils.ConvertNotifyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Positive;
import java.util.Collection;

@RestController
@RequestMapping("/notifyUser")
public class NotifyUserController {

    @Autowired
    private NotifyUserService notifyUserService;

    @GetMapping("/all")
    Collection<NotifyUser> findAll(){
        return notifyUserService.findAll();
    }

    @GetMapping(value = "/{notifyId}")
    public ResponseEntity<NotifyUserDTO> findNotifyBossById(@PathVariable @Positive Long notifyId){
        return notifyUserService.findById(notifyId)
                .map(ConvertNotifyUser::mapNotifyUser)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/create")
    public NotifyUserDTO create(NotifyUser notifyUser){
        return ConvertNotifyUser.mapNotifyUser(notifyUserService.create(notifyUser));
    }

}