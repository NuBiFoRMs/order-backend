package com.nubiform.order.service;

import com.nubiform.order.constant.ApiError;
import com.nubiform.order.domain.Member;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.Objects;
import java.util.function.Function;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    private final ModelMapper modelMapper;

    @Transactional(readOnly = true)
    public MemberResponse getMember(String userid) {
        return memberRepository.findByNicknameOrEmail(userid, userid)
                .map(member -> modelMapper.map(member, MemberResponse.class))
                .orElseThrow(() -> ApiException.of(ApiError.NO_DATA_FOUND));
    }

    @Transactional(readOnly = true)
    public Page<MemberOrderResponse> getMembers(Pageable pageable, String username, String email) {
        Function<Member, MemberOrderResponse> mapMemberOrder = member -> {
            OrderResponse orderResponse = member.getOrder().stream().max(Comparator.comparing(Order::getOrderDate))
                    .map(order -> modelMapper.map(order, OrderResponse.class))
                    .orElse(null);

            MemberResponse memberResponse = modelMapper.map(member, MemberResponse.class);

            return MemberOrderResponse.builder()
                    .member(memberResponse)
                    .lastOrder(orderResponse)
                    .build();
        };

        if (Objects.isNull(username) && Objects.isNull(email))
            return memberRepository.findAll(pageable)
                    .map(mapMemberOrder);
        else
            return memberRepository.findAllByUsernameOrEmail(username, email, pageable)
                    .map(mapMemberOrder);
    }
}
