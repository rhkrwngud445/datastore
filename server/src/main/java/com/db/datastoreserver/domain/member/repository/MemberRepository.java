package com.db.datastoreserver.domain.member.repository;

import com.db.datastoreserver.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
