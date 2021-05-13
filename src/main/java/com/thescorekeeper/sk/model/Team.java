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
    private String city;

    @Column
    private String parkName;

    @Column
    private Boolean isHome;

    @Column
    private String logo_URL;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getParkName() {
        return parkName;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName;
    }

    public Boolean getHome() {
        return isHome;
    }

    public void setHome(Boolean home) {
        isHome = home;
    }

    public String getLogo_URL() {
        return logo_URL;
    }

    public void setLogo_URL(String logo_URL) {
        this.logo_URL = logo_URL;
    }



    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", teamName='" + teamName + '\'' +
                ", city='" + city + '\'' +
                ", parkName='" + parkName + '\'' +
                ", isHome=" + isHome +
                ", logo_URL='" + logo_URL + '\'' +
                '}';
    }


}
