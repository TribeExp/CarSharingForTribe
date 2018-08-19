package com.basakdm.excartest.utils.converters;

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
        notificationsDTO.setTextNotify(notificationsEntity.getTextNotify());
        notificationsDTO.setId(notificationsEntity.getId());
        notificationsDTO.setFromWhomId(notificationsEntity.getFromWhomId());
        notificationsDTO.setOrderId(notificationsEntity.getOrderId());
        notificationsDTO.setToWhomId(notificationsEntity.getToWhomId());
        return notificationsDTO;
    }

    public static NotificationsEntity mapNotifyUser(NotificationsDTO notificationsDTO) {
        NotificationsEntity notificationsEntity;
        Optional<NotificationsEntity> optionalNotifyUser = notificationsService.findById(notificationsDTO.getId());

        if (optionalNotifyUser.isPresent()) notificationsEntity = optionalNotifyUser.get();
        else throw new RuntimeException("Incorrect ID of notification");

        notificationsEntity.setTextNotify(notificationsDTO.getTextNotify());
        notificationsEntity.setId(notificationsDTO.getId());
        notificationsEntity.setFromWhomId(notificationsDTO.getFromWhomId());
        notificationsEntity.setOrderId(notificationsDTO.getOrderId());
        notificationsEntity.setToWhomId(notificationsDTO.getToWhomId());
        return notificationsEntity;
    }
}
