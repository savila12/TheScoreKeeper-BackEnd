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

        Long coachId = myUserDetails.getUser().getId();
        Optional<Team> teamOfCoach = teamRepository.findById(coachId);

        Long TeamId;

        if (teamOfCoach.isEmpty()) {
            throw new DataNotFoundException("Coach doesn't have a team.");
        } else {
            TeamId = teamOfCoach.get().getId();
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
        newMember.setTeam(teamOfCoach.get());

        return memberRepository.save(newMember);
    }

    public Member getAMember(Long memberId){
        System.out.println("Calling service getAMember =====>");
        MyUserDetails myUserDetails =
                (MyUserDetails) SecurityContextHolder.getContext()
                        .getAuthentication().getPrincipal();

        Member member = this.memberRepository.findByIdAndTeamId(myUserDetails.getUser().getId(), memberId);

//        System.out.println(member);
        if(member == null){
            throw new DataNotFoundException("member with id: " + memberId + " doesn't exist");
        }
        else{
            return member;
        }
    }


    public String deleteTeamMember(Long memberId) {
        System.out.println("Calling service deleteMember --->");

        MyUserDetails myUserDetails =
                (MyUserDetails) SecurityContextHolder.getContext()
                        .getAuthentication().getPrincipal();


        Long teamId = myUserDetails.getUser().getId();
        System.out.println("this is the coach's team id:" + teamId);


        Optional<Member> member = memberRepository.findById(memberId);
        Member foundMember;

        if (member.isEmpty()) {
            throw new DataNotFoundException("No such member with id:" + memberId);
        } else {
            foundMember = member.get();
            if(foundMember.getTeam().getId().equals(teamId)){
                memberRepository.deleteById(memberId);
                return "Successfully Deleted";
            }else{
                throw new DataNotFoundException("Member is not on this team");
            }
        }
    }
} // END OF CLASS
