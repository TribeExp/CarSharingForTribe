package com.basakdm.excartest.utils;

import com.basakdm.excartest.dto.NotifyAdminDTO;
import com.basakdm.excartest.entity.NotifyAdmin;
import com.basakdm.excartest.service.NotifyAdminService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ConvertNotifyAdmin {

    @Autowired
    private static NotifyAdminService notifyAdminService;

    public static NotifyAdminDTO mapNotifyAdmin(NotifyAdmin notifyAdmin) {
        NotifyAdminDTO notifyAdminDTO = new NotifyAdminDTO();
        notifyAdminDTO.setText_notify(notifyAdmin.getText_notify());
        notifyAdminDTO.setId(notifyAdmin.getId());
        notifyAdminDTO.setId_user_plea(notifyAdmin.getId_user_plea());
        return notifyAdminDTO;
    }

    public static NotifyAdmin mapNotifyAdmin(NotifyAdminDTO notifyAdminDTO) {
        NotifyAdmin notifyAdmin;
        Optional<NotifyAdmin> optionalNotifyAdmin = notifyAdminService.findById(notifyAdminDTO.getId());

        if (optionalNotifyAdmin.isPresent()) notifyAdmin = optionalNotifyAdmin.get();
        else throw new RuntimeException("Incorrect ID of car");

        notifyAdmin.setText_notify(notifyAdminDTO.getText_notify());
        notifyAdmin.setId(notifyAdminDTO.getId());
        notifyAdmin.setId_user_plea(notifyAdminDTO.getId_user_plea());

        return notifyAdmin;
    }
}
