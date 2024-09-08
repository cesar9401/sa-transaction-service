package com.cesar31.transaction.application.util.enums;

import java.util.UUID;

public enum RoleEnum {
    ROOT(UUID.fromString("c9742ad2-842c-4279-a839-9659c9328c29")),
    HOTEL_MANAGER(UUID.fromString("926a2507-3a4e-48b6-a96d-a0538681a084")),
    RESTAURANT_MANAGER(UUID.fromString("025386dd-ccdf-4432-b36a-bae7ef03b211")),
    EMPLOYEE(UUID.fromString("443cc467-2f70-452c-b206-5e523fb0a22f")),
    CLIENT(UUID.fromString("70eed8c6-5d01-403d-82a4-b64e8b735c1d"));

    public final UUID roleId;

    RoleEnum(UUID roleId) {
        this.roleId = roleId;
    }
}
