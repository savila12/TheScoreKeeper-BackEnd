package com.thescorekeeper.sk.controller;

import com.thescorekeeper.sk.model.Team;
import com.thescorekeeper.sk.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="/api")
public class TeamController {

    private TeamService teamService;

    @Autowired
    public void setTeamService(TeamService teamService){
        this.teamService = teamService;
    }


    @GetMapping("/teams")
    public List<Team> getAllTeams() {
        System.out.println("Calling  getAllTeams -->");
        return teamService.getAllTeams();
    }

    @GetMapping("/teams/members")
    public List<Member> getAllMembers(){}




}
