package com.basakdm.excartest.utils;

import com.basakdm.excartest.dto.NotifyBossDTO;
import com.basakdm.excartest.entity.NotifyBoss;
import com.basakdm.excartest.service.NotifyBossService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ConvertNotifyBoss {

    @Autowired
    private static NotifyBossService notifyBossService;

    public static NotifyBossDTO mapNotifyBoss(NotifyBoss notifyBoss) {
        NotifyBossDTO notifyBossDTO = new NotifyBossDTO();
        return notifyBossDTO;
    }

    public static NotifyBoss mapNotifyBoss(NotifyBossDTO notifyBossDTO) {
        NotifyBoss notifyBoss;
        Optional<NotifyBoss> optionalNotifyBoss = notifyBossService.findById(notifyBossDTO.getId());

        if (optionalNotifyBoss.isPresent()) notifyBoss = optionalNotifyBoss.get();
        else throw new RuntimeException("Incorrect ID of car");
        return notifyBoss;
    }
}
