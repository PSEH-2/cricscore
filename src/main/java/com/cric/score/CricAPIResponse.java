package com.cric.score;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder
public class CricAPIResponse {

    @JsonProperty("Team-1")
    private String team1;

    @JsonProperty("Team-2")
    private String team2;

    @JsonProperty("Winning team’s score:")
    private String score;

    @JsonProperty("Round rotation")
    private String rotation;

    public String getRotation() {
        return rotation;
    }

    public void setRotation(String rotation) {
        this.rotation = rotation;
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
                "rotation='" + rotation + '\'' +
                ", score='" + score + '\'' +
                ", team1='" + team1 + '\'' +
                ", team2='" + team2 + '\'' +
                '}';
    }
}
