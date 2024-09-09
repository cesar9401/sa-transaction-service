package com.cesar31.transaction.application.ports.output;

import java.util.UUID;

public interface ExistsClientOutputPort {

    Boolean existsClientById(UUID clientId);
}
