package com.ajou_nice.with_pet.controller;

import com.ajou_nice.with_pet.domain.dto.Response;
import com.ajou_nice.with_pet.domain.dto.party.PartyInfoResponse;
import com.ajou_nice.with_pet.domain.dto.party.PartyMemberRequest;
import com.ajou_nice.with_pet.domain.dto.party.PartyRequest;
import com.ajou_nice.with_pet.service.PartyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/groups")
@Slf4j
@Api(tags = "Party API")
public class PartyController {

    private final PartyService partyService;

    @PostMapping("/member")
    @ApiOperation(value = "그룹 멤버 추가")
    public Response<PartyInfoResponse> addMember(@ApiIgnore Authentication authentication,
            @RequestBody PartyMemberRequest partyMemberRequest) {
        return Response.success(
                partyService.addMember(authentication.getName(), partyMemberRequest));
    }

    @GetMapping("/group-infos")
    @ApiOperation(value = "그룹 상세 리스트 조회")
    public Response<List<PartyInfoResponse>> getPartyInfoList(
            @ApiIgnore Authentication authentication) {
        return Response.success(partyService.getPartyInfoList(authentication.getName()));
    }

    @PostMapping
    @ApiOperation("그룹 생성")
    public Response<PartyInfoResponse> createParty(@ApiIgnore Authentication authentication,
            @RequestBody PartyRequest partyRequest) {

        return Response.success(partyService.createParty(authentication.getName(), partyRequest));
    }

    @DeleteMapping("/{partyId}")
    @ApiOperation(value = "그룹 탈퇴")
    public Response leaveParty(@ApiIgnore Authentication authentication,
            @PathVariable Long partyId) {
        return Response.success(partyService.leaveParty(authentication.getName(), partyId));
    }

    @DeleteMapping("/{partyId}/members/{memberId}")
    @ApiOperation(value = "그룹 멤버 방출")
    public Response expelMember(@ApiIgnore Authentication authentication,
            @PathVariable Long partyId, @PathVariable Long memberId) {
        return Response.success(
                partyService.expelMember(authentication.getName(), partyId, memberId));
    }


}
