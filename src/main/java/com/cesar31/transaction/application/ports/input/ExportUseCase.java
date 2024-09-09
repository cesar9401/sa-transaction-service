package com.cesar31.transaction.application.ports.input;

import java.time.LocalDateTime;
import java.util.UUID;

public interface ExportUseCase {

    byte[] getTopOrganizationByIncome(LocalDateTime start, LocalDateTime end) throws Exception;

    byte[] getTransactionByClient(UUID clientId, UUID organizationId, LocalDateTime start, LocalDateTime end) throws Exception;
}
