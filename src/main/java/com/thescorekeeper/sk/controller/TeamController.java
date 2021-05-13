package com.thescorekeeper.sk.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api")
public class TeamController {


    @GetMapping("/teams")
    public String getAllTeams() {
        System.out.println("Calling  getAllTeams -->");
        return "ALL TEAMS";
    }


}
