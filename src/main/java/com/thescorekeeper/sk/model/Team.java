package com.thescorekeeper.sk.model;

import javax.persistence.*;

@Entity
@Table(name = "team")
public class Team {


    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String teamName;

    @Column
    private String teamLocation;

    @Column
    private Boolean home;

    @Column
    private String logo_URL;


}
