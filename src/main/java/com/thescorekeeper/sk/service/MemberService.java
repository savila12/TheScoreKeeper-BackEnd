package com.thescorekeeper.sk.service;


import com.thescorekeeper.sk.exception.DataNotFoundException;
import com.thescorekeeper.sk.model.Member;
import com.thescorekeeper.sk.model.Team;
import com.thescorekeeper.sk.repository.MemberRepository;
import com.thescorekeeper.sk.repository.TeamRepository;
import com.thescorekeeper.sk.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private TeamRepository teamRepository;
    private MemberRepository memberRepository;

    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Autowired
    public void setTeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }


    public Member addTeamMember(Member member) {

        MyUserDetails myUserDetails =
                (MyUserDetails) SecurityContextHolder.getContext()
                        .getAuthentication().getPrincipal();

        Long teamId = myUserDetails.getUser().getId();
        Optional<Team> team = teamRepository.findById(teamId);

        Team teamOfCoach;

        if (team.isEmpty()) {
            throw new DataNotFoundException("No team found.");
        } else {
            teamOfCoach = team.get();
        }

        Member newMember = new Member();

        newMember.setAddress(member.getAddress());
        newMember.setFirstName(member.getFirstName());
        newMember.setTeam(teamOfCoach);

        return memberRepository.save(newMember);


    }


    public Member deleteTeamMember(Long memberId) {

        MyUserDetails myUserDetails =
                (MyUserDetails) SecurityContextHolder.getContext()
                        .getAuthentication().getPrincipal();

        Long teamId = myUserDetails.getUser().getId();

        Optional<Member> member = memberRepository.findById(memberId);

        Member memberToDelete;

        if (member == null) {
            throw new DataNotFoundException("No such member with id:" + memberId);
        } else {

            //memberRepository.deleteById(memberId);
            memberToDelete = member.get();
        }

        if (memberToDelete.getTeam().getId() == teamId){
            memberRepository.deleteById(memberId);
            return memberToDelete;
        }else{
            throw new DataNotFoundException("Member not on team.");
        }

    }

} // END OF CLASS
