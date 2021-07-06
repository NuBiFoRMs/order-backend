package com.nubiform.order.repository;

import com.nubiform.order.domain.Member;
import com.nubiform.order.domain.QMember;
import com.nubiform.order.domain.QOrder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class MemberQueryRepositoryImpl extends QuerydslRepositorySupport implements MemberQueryRepository {

    public MemberQueryRepositoryImpl() {
        super(Member.class);
    }

    @Override
    public List<Member> getMember() {
        QMember member = QMember.member;
        QOrder order = QOrder.order;

        JPQLQuery<Member> query = from(member)
                .leftJoin(member.order, order)
                .orderBy(member.id.asc(), order.orderDate.desc());
        return query.fetch();
    }
}
