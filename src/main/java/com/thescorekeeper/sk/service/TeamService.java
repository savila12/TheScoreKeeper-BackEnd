package com.thescorekeeper.sk.service;

import com.thescorekeeper.sk.model.Team;
import com.thescorekeeper.sk.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

    TeamRepository teamRepository;

    @Autowired
    public void setTeamRepository(TeamRepository teamRepository){
        this.teamRepository = teamRepository;
    }

    public List<Team> getAllTeams(){
        System.out.println("calling service getAllTeams =====>");
        return teamRepository.findAll();
    }


}
