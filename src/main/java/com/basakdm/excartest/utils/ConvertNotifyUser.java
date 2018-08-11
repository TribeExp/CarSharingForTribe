package com.basakdm.excartest.utils;

import com.basakdm.excartest.dto.NotifyUserDTO;
import com.basakdm.excartest.entity.NotifyUser;
import com.basakdm.excartest.service.NotifyUserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ConvertNotifyUser {

    @Autowired
    private static NotifyUserService notifyUserService;

    public static NotifyUserDTO mapNotifyUser(NotifyUser notifyUser) {
        NotifyUserDTO notifyUserDTO = new NotifyUserDTO();
        return notifyUserDTO;
    }

    public static NotifyUser mapNotifyUser(NotifyUserDTO notifyUserDTO) {
        NotifyUser notifyUser;
        Optional<NotifyUser> optionalNotifyUser = notifyUserService.findById(notifyUserDTO.getId());

        if (optionalNotifyUser.isPresent()) notifyUser = optionalNotifyUser.get();
        else throw new RuntimeException("Incorrect ID of car");
        return notifyUser;
    }
}
