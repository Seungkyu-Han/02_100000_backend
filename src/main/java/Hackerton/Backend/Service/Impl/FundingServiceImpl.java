package Hackerton.Backend.Service.Impl;

import Hackerton.Backend.Repository.FundingRepository;
import Hackerton.Backend.Service.FundingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FundingServiceImpl implements FundingService {

    private final FundingRepository fundingRepository;
}
