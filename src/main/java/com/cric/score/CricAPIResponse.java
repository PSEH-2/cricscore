package com.cric.score;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CricAPIResponse {

    private int status;

    @JsonProperty("Round rotation")
    private String stat;

    @JsonProperty("Winning team’s score:")
    private String score;

    @JsonProperty("Team-1")
    private String team1;

    @JsonProperty("Team-2")
    private String team2;

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    @Override
    public String toString() {
        return "CricAPIResponse{" +
                "stat='" + stat + '\'' +
                ", score='" + score + '\'' +
                ", team1='" + team1 + '\'' +
                ", team2='" + team2 + '\'' +
                '}';
    }

    public CricAPIResponse(String stat, String score, String team1, String team2) {
        this.stat = stat;
        this.score = score;
        this.team1 = team1;
        this.team2 = team2;
    }

    public CricAPIResponse(){}
}
