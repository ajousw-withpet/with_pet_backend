package com.ajou_nice.with_pet.controller.user;

import com.ajou_nice.with_pet.domain.dto.Response;
import com.ajou_nice.with_pet.domain.dto.auth.UserLoginRequest;
import com.ajou_nice.with_pet.domain.dto.auth.UserLoginResponse;
import com.ajou_nice.with_pet.domain.dto.auth.UserSignUpRequest;
import com.ajou_nice.with_pet.domain.dto.auth.UserSignUpResponse;
import com.ajou_nice.with_pet.service.user.UserAuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
@Api(tags = "UserAuth API")
@Slf4j
public class UserAuthController {

    private final UserAuthService userService;

    @PostMapping("/signup")
    @ApiOperation(value = "회원가입")
    public Response<UserSignUpResponse> signUp(
            @Valid @RequestBody UserSignUpRequest userSignUpRequest) {
        log.info(
                "--------------------------User SignUp Request : {} -------------------------------",
                userSignUpRequest);
        UserSignUpResponse userSignUpResponse = userService.signUp(userSignUpRequest);
        log.info(
                "--------------------------User SignUp Response : {} -------------------------------",
                userSignUpRequest);
        return Response.success(userSignUpResponse);
    }

    @PostMapping("/login")
    @ApiOperation(value = "로그인")
    public Response<UserLoginResponse> login(@RequestBody UserLoginRequest userLoginRequest,
            HttpServletResponse response) {
        log.info(
                "--------------------------User Login Request : {} -------------------------------",
                userLoginRequest);
        UserLoginResponse userLoginResponse = userService.login(userLoginRequest, response);
        log.info(
                "--------------------------User Login Response : {} -------------------------------",
                userLoginResponse);
        return Response.success(userLoginResponse);
    }

    @GetMapping("/logout")
    @ApiOperation(value = "로그아웃")
    public Response logout(@ApiIgnore Authentication authentication,
            HttpServletResponse httpServletResponse) {
        userService.logout(httpServletResponse);
        return Response.success("로그아웃되었습니다.");
    }
}
