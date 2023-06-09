package com.ajou_nice.with_pet.controller;


import com.ajou_nice.with_pet.domain.dto.Response;
import com.ajou_nice.with_pet.domain.dto.kakaopay.PayApproveResponse;
import com.ajou_nice.with_pet.domain.dto.kakaopay.PayCancelResponse;
import com.ajou_nice.with_pet.domain.dto.kakaopay.PayReadyResponse;
import com.ajou_nice.with_pet.domain.dto.kakaopay.PayRequest.PaySimpleRequest;
import com.ajou_nice.with_pet.domain.dto.kakaopay.RefundResponse;
import com.ajou_nice.with_pet.service.KaKaoPayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.net.URI;
import java.net.URISyntaxException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@Slf4j
@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
@Api(tags = "KaKaoPay API")
public class KaKaoPayController {

    private final KaKaoPayService kaKaoPayService;

    //결제 요청 -> 클라이언트에서는
    @PostMapping("/ready")
    @ApiOperation(value = "결제 요청")
    public Response<PayReadyResponse> readyToKakaoPay(@ApiIgnore Authentication authentication,
            @RequestBody PaySimpleRequest paySimpleRequest) {

        log.info("=======================payRequest : {}=============================",
                paySimpleRequest);
        //reservation과 동기화 필요
        PayReadyResponse payReadyResponse = kaKaoPayService.payReady(authentication.getName(),
                paySimpleRequest.getReservationId());
        log.info("=======================payResponse : {}=============================",
                payReadyResponse);
        return Response.success(payReadyResponse);
    }

    //결제 성공
    @GetMapping("/success")
    @ApiOperation(value = "결제 성공")
    public Response<PayApproveResponse> afterPay(@ApiIgnore Authentication authentication,
            @RequestParam("pg_token") String pgToken, @RequestParam("tid") String tid) {
        PayApproveResponse payApproveResponse = kaKaoPayService.approvePay(authentication.getName(),
                pgToken, tid);

        log.info("=======================paySuccessResponse : {}=============================",
                payApproveResponse);
        return Response.success(payApproveResponse);
    }

    //결제 환불 -> 사용자의 결제 취소를 담당
    @PostMapping("/refund")
    @ApiOperation(value = "사용자의 결제 취소(환불)")
    public Response<RefundResponse> refundPay(@ApiIgnore Authentication authentication,
            @RequestBody PaySimpleRequest paySimpleRequest) {

        log.info("=======================payCancelRequest : {}=============================",
                paySimpleRequest);

        RefundResponse refundResponse = kaKaoPayService.refundPayment(authentication.getName(),
                paySimpleRequest.getReservationId());

        log.info("=======================payCancelResponse : {}=============================",
                refundResponse);

        return Response.success(refundResponse);
    }

}
