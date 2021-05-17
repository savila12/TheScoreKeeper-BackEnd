package com.thescorekeeper.sk.repository;

import com.thescorekeeper.sk.model.Member;
import com.thescorekeeper.sk.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    Team findByTeamName(String team);
    List<Member> findByTeamName(Long id);
    Member findByUserIdAndId(Long userId, Long memberId);

}
