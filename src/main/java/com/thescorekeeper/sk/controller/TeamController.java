package com.thescorekeeper.sk.controller;

import com.thescorekeeper.sk.model.Member;
import com.thescorekeeper.sk.model.Team;
import com.thescorekeeper.sk.service.MemberService;
import com.thescorekeeper.sk.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api")
public class TeamController {

//    // Test stuff
//    private TeamRepository teamRepository;
//
//    @Autowired
//    public void setTeamRepo(TeamRepository teamRepository) {
//        this.teamRepository = teamRepository;
//    }
//
//    // Tested endpoint to see if we could grab a team name from database
//    @GetMapping("/teams")
//    public String getAllTeams() {
//        System.out.println("Calling  getAllTeams -->");
//        Team team = teamRepository.findByTeamName("bears");
//        String test1 = team.getTeamName();
//        return "here is the team name: " + test1;
//    }
//
//    // End of Test stuff

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


    @GetMapping("/teams")
    public List<Team> getAllTeams() {
        System.out.println("Calling getAllTeams -->");
        return teamService.getAllTeams();
    }

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

    @PostMapping("/teams/members")
    public Member addTeamMember(@RequestBody Member member){
        System.out.println("Calling addTeamMember -->");
        return memberService.addTeamMember(member);
    }

    @DeleteMapping("/teams/members/{memberId}")
    public Member deleteTeamMember(@PathVariable Long memberId){
        System.out.println("Calling deleteTeamMember -->");
        return memberService.deleteTeamMember(memberId);
    }




}
