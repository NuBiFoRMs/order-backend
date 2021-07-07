package com.nubiform.order.service;

import com.nubiform.order.constant.ApiError;
import com.nubiform.order.domain.Member;
import com.nubiform.order.domain.Order;
import com.nubiform.order.exception.ApiException;
import com.nubiform.order.exception.ApiNotFoundException;
import com.nubiform.order.repository.MemberRepository;
import com.nubiform.order.repository.OrderRepository;
import com.nubiform.order.vo.request.OrderRequest;
import com.nubiform.order.vo.response.OrderResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class OrderService {

    private final MemberRepository memberRepository;

    private final OrderRepository orderRepository;

    private final ModelMapper modelMapper;

    @Transactional(readOnly = true)
    public List<OrderResponse> getOrder(String userid) {
        return memberRepository.findByNicknameOrEmail(userid, userid)
                .orElseThrow(() -> ApiNotFoundException.of(ApiError.ORDER_NOT_FOUND))
                .getOrder().stream()
                .map(order -> modelMapper.map(order, OrderResponse.class))
                .collect(Collectors.toList());
    }

    public OrderResponse order(String userid, OrderRequest orderRequest) {
        Member member = memberRepository.findByNicknameOrEmail(userid, userid)
                .orElseThrow(() -> ApiException.of(ApiError.NO_DATA_FOUND));
        Order order = modelMapper.map(orderRequest, Order.class);
        order.setMember(member);
        order.setOrderDate(LocalDateTime.now());
        Order newOrder = orderRepository.save(order);
        return modelMapper.map(newOrder, OrderResponse.class);
    }
}
