package com.thescorekeeper.sk.service;


import com.thescorekeeper.sk.exception.DataNotFoundException;
import com.thescorekeeper.sk.model.Member;
import com.thescorekeeper.sk.model.Team;
import com.thescorekeeper.sk.model.User;
import com.thescorekeeper.sk.repository.MemberRepository;
import com.thescorekeeper.sk.repository.TeamRepository;
import com.thescorekeeper.sk.repository.UserRepository;
import com.thescorekeeper.sk.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class TeamService {


    private MemberRepository memberRepository;
    private TeamRepository teamRepository;

    @Autowired
    public void setTeamRepository(TeamRepository teamRepository){
        this.teamRepository = teamRepository;
    }

    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // Get all teams
    // http://localhost:9092/api/teams
    public List<Team> getAllTeams(){
        System.out.println("calling the service 'getAllTeams' ->");
        List<Team> teamList = teamRepository.findAll();
        if(teamList.isEmpty()) {
            throw new DataNotFoundException("No teams found.");
        } else {
            return teamList;
        }
    }


    public List<Member> getAllMembers(){
        System.out.println("calling the service 'getAllMembers' ->");

        MyUserDetails myUserDetails =
                (MyUserDetails) SecurityContextHolder.getContext()
                        .getAuthentication().getPrincipal();

        System.out.println("this is the myUserDetails: " + myUserDetails.getUser().getId());

        Optional<Team> team = teamRepository.findById(myUserDetails.getUser().getId());

        Long teamOfCoach;

        if( team.isEmpty()){
            throw new DataNotFoundException("No team found.");
        } else {
            teamOfCoach = team.get().getId();
        }

        List<Member> m1 = memberRepository.findByTeamId(teamOfCoach);

        return m1;

    }

    public Team createTeam(Team team){

        System.out.println("calling the service 'createTeam' ->");

        MyUserDetails myUserDetails =
                (MyUserDetails) SecurityContextHolder.getContext()
                        .getAuthentication().getPrincipal();

        Team newTeam = new Team();

        newTeam.setCity(team.getCity());
        newTeam.setHome(team.getHome());
        newTeam.setLogo_URL(team.getLogo_URL());
        newTeam.setParkName(team.getParkName());
        newTeam.setTeamName(team.getTeamName());
        newTeam.setUser(myUserDetails.getUser());

        teamRepository.save(newTeam);

        return newTeam;


    }




} // END OF CLASS
