package com.nubiform.order.service;

import com.nubiform.order.constant.ApiError;
import com.nubiform.order.exception.ApiException;
import com.nubiform.order.repository.MemberRepository;
import com.nubiform.order.vo.response.MemberResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    private final ModelMapper modelMapper;

    public Page<MemberResponse> getMembers(Pageable pageable) {
        return memberRepository.findAll(pageable)
                .map(member -> modelMapper.map(member, MemberResponse.class));
    }

    public MemberResponse getMember(String userid) {
        return memberRepository.findByNickname(userid)
                .map(member -> modelMapper.map(member, MemberResponse.class))
                .orElseThrow(() -> new ApiException(ApiError.ERROR));
    }
}
