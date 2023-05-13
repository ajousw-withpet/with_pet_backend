package com.ajou_nice.with_pet.domain.dto.dog;

import com.ajou_nice.with_pet.domain.dto.party.PartyMemberResponse;
import com.ajou_nice.with_pet.domain.entity.Dog;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class DogInfoResponse {

    private Long dog_id;
    private String dog_img;
    private String dog_name;
    private String dog_breed;
    private String dog_gender;
    private Boolean neutralization;
    private LocalDate dog_birth;
    private Float dog_weight;
    private String dog_isbn;
    private List<PartyMemberResponse> partyMemberResponses;

    public static List<DogInfoResponse> toList(List<Dog> dogs) {
        return dogs.stream().map(dog -> DogInfoResponse.builder()
                .dog_id(dog.getDogId())
                .dog_img(dog.getProfile_img())
                .dog_name(dog.getName())
                .dog_breed(dog.getBreed())
                .dog_gender(dog.getGender())
                .neutralization(dog.getNeutralization())
                .dog_birth(dog.getBirth())
                .dog_weight(dog.getWeight())
                .dog_isbn(dog.getIsbn())
                .build()).collect(Collectors.toList());
    }

    public static DogInfoResponse of(Dog dog) {

        return DogInfoResponse.builder()
                .dog_id(dog.getDogId())
                .dog_img(dog.getProfile_img())
                .dog_name(dog.getName())
                .dog_breed(dog.getBreed())
                .dog_gender(dog.getGender())
                .neutralization(dog.getNeutralization())
                .dog_birth(dog.getBirth())
                .dog_weight(dog.getWeight())
                .dog_isbn(dog.getIsbn())
                .partyMemberResponses(
                        dog.getParty().getUserPartyList().stream().map(PartyMemberResponse::of)
                                .collect(
                                        Collectors.toList()))
                .build();
    }
}
