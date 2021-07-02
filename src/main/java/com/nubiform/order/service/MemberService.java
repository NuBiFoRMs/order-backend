package com.nubiform.order.service;

import com.nubiform.order.constant.ApiError;
import com.nubiform.order.domain.Order;
import com.nubiform.order.exception.ApiException;
import com.nubiform.order.repository.MemberRepository;
import com.nubiform.order.vo.response.MemberOrderResponse;
import com.nubiform.order.vo.response.MemberResponse;
import com.nubiform.order.vo.response.OrderResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Comparator;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    private final ModelMapper modelMapper;

    public MemberResponse getMember(String userid) {
        return memberRepository.findByNickname(userid)
                .map(member -> modelMapper.map(member, MemberResponse.class))
                .orElseThrow(() -> new ApiException(ApiError.ERROR));
    }

    public Page<MemberOrderResponse> getMembers(Pageable pageable, String username, String email) {
        return memberRepository.findByUsernameOrEmail(username, email, pageable)
                .map(member -> {
                    OrderResponse orderResponse = member.getOrder().stream()
                            .sorted(Comparator.comparing(Order::getOrderDate).reversed())
                            .findFirst()
                            .map(order -> modelMapper.map(order, OrderResponse.class))
                            .orElse(null);

                    MemberResponse memberResponse = modelMapper.map(member, MemberResponse.class);

                    return MemberOrderResponse.builder()
                            .member(memberResponse)
                            .lastOrder(orderResponse)
                            .build();
                });
    }
}
