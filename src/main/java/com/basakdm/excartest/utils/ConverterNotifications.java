package com.basakdm.excartest.utils;

import com.basakdm.excartest.dto.NotificationsDTO;
import com.basakdm.excartest.entity.NotificationsEntity;
import com.basakdm.excartest.service.NotificationsService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ConverterNotifications {

    @Autowired
    private static NotificationsService notificationsService;

    public static NotificationsDTO mapNotifyUser(NotificationsEntity notificationsEntity) {
        NotificationsDTO notificationsDTO = new NotificationsDTO();
        notificationsDTO.setText_notify(notificationsEntity.getText_notify());
        notificationsDTO.setId(notificationsEntity.getId());
        return notificationsDTO;
    }

    public static NotificationsEntity mapNotifyUser(NotificationsDTO notificationsDTO) {
        NotificationsEntity notificationsEntity;
        Optional<NotificationsEntity> optionalNotifyUser = notificationsService.findById(notificationsDTO.getId());

        if (optionalNotifyUser.isPresent()) notificationsEntity = optionalNotifyUser.get();
        else throw new RuntimeException("Incorrect ID of car");

        notificationsEntity.setText_notify(notificationsDTO.getText_notify());
        notificationsEntity.setId(notificationsDTO.getId());
        return notificationsEntity;
    }
}
