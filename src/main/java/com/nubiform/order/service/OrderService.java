package com.nubiform.order.service;

import com.nubiform.order.constant.ApiError;
import com.nubiform.order.exception.ApiException;
import com.nubiform.order.repository.MemberRepository;
import com.nubiform.order.vo.response.OrderResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderService {

    private final MemberRepository memberRepository;

    private final ModelMapper modelMapper;

    public List<OrderResponse> getOrder(String userid) {
        return memberRepository.findByNicknameOrEmail(userid, userid)
                .orElseThrow(() -> new ApiException(ApiError.NO_DATA_FOUND))
                .getOrder().stream()
                .map(order -> modelMapper.map(order, OrderResponse.class))
                .collect(Collectors.toList());
    }
}
