package com.nubiform.order.repository;

import com.nubiform.order.domain.Member;

import java.util.List;

public interface MemberQueryRepository {

    List<Member> getMember();
}
