package com.thescorekeeper.sk.controller;

import com.thescorekeeper.sk.model.Team;
import com.thescorekeeper.sk.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.thescorekeeper.sk.model.Member;
import com.thescorekeeper.sk.service.MemberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api")
public class TeamController {

    private TeamService teamService;
    private MemberService memberService;

    @Autowired
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }

    @Autowired
    public void setTeamsService(TeamService teamService){
        this.teamService = teamService;
    }

    //http://localhost:9092/api/teams
    @GetMapping("/teams")
    public List<Team> getAllTeams() {
        System.out.println("Calling  getAllTeams -->");
        return teamService.getAllTeams();
    }


    //http://localhost:9092/api/teams/members

    @PostMapping("/teams")
    public Team createTeam(@RequestBody Team team) {
        System.out.println("Calling createTeam -->");
        return teamService.createTeam(team);
    }


    @GetMapping("/teams/members")
    public List<Member> getAllTeamMembers() {
        System.out.println("Calling getAllTeamMembers -->");
        return teamService.getAllMembers();
    }

    @GetMapping("/teams/members/{memberId}")
    public Member getAMember(@PathVariable Long memberId){
        System.out.println("Calling getAMember =====>");
        return memberService.getAMember(memberId);
    }

    //http://localhost:9092/api/teams/members
    @PostMapping("/teams/members")
    public Member addTeamMember(@RequestBody Member member){
        System.out.println("Calling addTeamMember -->");
        return memberService.addTeamMember(member);
    }

    //http://localhost:9092/api/teams/members/memberId
    @DeleteMapping("/teams/members/{memberId}")
    public String deleteTeamMember(@PathVariable Long memberId){
        System.out.println("Calling deleteTeamMember -->");
        return memberService.deleteTeamMember(memberId);
    }
}
