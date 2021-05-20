package com.thescorekeeper.sk.repository;


import com.thescorekeeper.sk.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository <Member, Long> {

    List<Member> findByTeamId(Long id);
    Member findByIdAndTeamId(Long teamId, Long memberId);


}
