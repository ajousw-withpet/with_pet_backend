package com.ajou_nice.with_pet.domain.entity.embedded;

import com.ajou_nice.with_pet.domain.dto.embedded.AddressDto;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Address {

    private String zipcode;
    private String streetAdr;
    private String detailAdr;



    public static Address toAddressEntity(AddressDto addressDto) {
        return Address.builder()
                .zipcode(addressDto.getZipcode())
                .streetAdr(addressDto.getStreetAdr())
                .detailAdr(addressDto.getDetailAdr())
                .build();
    }
}
