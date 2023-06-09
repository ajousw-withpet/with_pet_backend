package com.ajou_nice.with_pet.domain.dto.reservation;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class ReservationStatusRequest {

    @NotNull
    private Long reservationId;
    @NotBlank
    private String status;
}
