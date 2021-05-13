package com.thescorekeeper.sk.controller;

import com.thescorekeeper.sk.model.Team;
import com.thescorekeeper.sk.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api")
public class TeamController {

    private TeamRepository teamRepository;

    @Autowired
    public void setTeamRepo(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    // Tested endpoint to see if we could grab a team name from database
    @GetMapping("/teams")
    public String getAllTeams() {
        System.out.println("Calling  getAllTeams -->");
        Team team = teamRepository.findByTeamName("bears");
        String test1 = team.getTeamName();
        return "here is the team name: " + test1;
    }


}
