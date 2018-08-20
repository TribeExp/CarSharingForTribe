package com.admin_team.carsharing.request_models.user_models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class UserIdAndCarId {
    private Long userId;
    private Long carId;
}
