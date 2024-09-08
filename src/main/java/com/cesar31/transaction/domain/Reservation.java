package com.cesar31.transaction.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class Reservation extends Transaction {

    private UUID roomId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Category catReservationStatus;
}
