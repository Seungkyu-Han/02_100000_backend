package Hackerton.Backend.Controller;

import Hackerton.Backend.Service.FundingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/funding")
@RequiredArgsConstructor
@Tag(name = "펀딩 관련 API")
public class FundingController {

    private final FundingService fundingService;
}
