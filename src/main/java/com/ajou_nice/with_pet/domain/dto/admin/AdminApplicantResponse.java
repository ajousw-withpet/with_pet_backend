package com.ajou_nice.with_pet.domain.dto.admin;



import com.ajou_nice.with_pet.domain.entity.User;
import com.ajou_nice.with_pet.enums.ApplicantStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString
public class AdminApplicantResponse {

	private Long user_id;
	private String applicant_userName;
	private ApplicantStatus applicantStatus;

	public static AdminApplicantResponse of(User user){
		return AdminApplicantResponse.builder()
				.user_id(user.getUserId())
				.applicant_userName(user.getName())
				.applicantStatus(user.getApplicantStatus())
				.build();
	}
}
