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
        System.out.println("Calling service addTeamMember --->");
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
        newMember.setLastName(member.getLastName());
        newMember.setEmail(member.getEmail());
        newMember.setBattingOrder(member.getBattingOrder());
        newMember.setPhoneNumber(member.getPhoneNumber());
        newMember.setPosition(member.getPosition());
        newMember.setRole(member.getRole());
        newMember.setPlayerNumber(member.getPlayerNumber());
        newMember.setTeam(teamOfCoach);

        return memberRepository.save(newMember);
    }


    public String deleteTeamMember(Long memberId) {
        System.out.println("Calling service deleteMember --->");
        MyUserDetails myUserDetails =
                (MyUserDetails) SecurityContextHolder.getContext()
                        .getAuthentication().getPrincipal();

        Long teamId = myUserDetails.getUser().getId();

        Optional<Member> member = memberRepository.findById(memberId);

        if (member.isEmpty()) {
            throw new DataNotFoundException("No such member with id:" + memberId);
        } else {
            if(member.get().getId().equals(teamId)){
                memberRepository.deleteById(memberId);
                return "Successfully Deleted";
            }else{
                throw new DataNotFoundException("Member is not on this team");
            }
        }
    }
} // END OF CLASS
