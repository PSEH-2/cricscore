package com.cric.score;

import com.cric.EntityNotFound;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import sun.misc.Regexp;

import javax.ws.rs.core.MediaType;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CricAPIService {

    private static final String CRIC_API = "http://cricapi.com/api/cricketScore?unique_id=%s";
    private static final String API_HEADER = "apikey";
    private static final String API_KEY = "WmPJrX2s3KMyZVPFwlm1vxXLXKw1";
    private JSONParser jsonParser = new JSONParser();

    public CricAPIResponse processUniqueId(Integer uniqueId) throws EntityNotFound{
        CricAPIResponse result = null;
        try {
            JSONObject output = getOutput(uniqueId);
            result = new CricAPIResponse();
            String score = (String) output.get("score");
            String team1 = (String) output.get("team-1");
            String team2 = (String) output.get("team-2");
            String stat = (String) output.get("stat");
            String pattern = null;
            String compareTeam = null;
            if(team1.contains(" ")) compareTeam = team1.substring(0,team1.indexOf(" "));
            else compareTeam = team1;
            if (stat.startsWith(compareTeam)){
                pattern = team1 + "\\s(\\d+.\\d)";
                team1 = team1 + "(winner)";
            } else{
                pattern = team2 + "\\s(\\d+.\\d)";
                team2 = team2 + "(winner)";
            }
            Pattern regex = Pattern.compile(pattern);
            Matcher matcher = regex.matcher(score);
            result.setTeam1(team1);
            result.setTeam2(team2);
            if(matcher.find()) score = matcher.group(1);
            result.setScore(score);
            String[] rotations = score.split("/");
            String rotation = rotations[1] + rotations[0]+"/";
            result.setRotation(rotation);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    private JSONObject getOutput(Integer uniqueId) throws ParseException,EntityNotFound {
        Client client = Client.create();
        WebResource webResource = client.resource(String.format(CRIC_API,uniqueId));
        ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON)
                .header(API_HEADER,API_KEY)
                .get(ClientResponse.class);
        if (response.getStatus() != 200) {
            throw new EntityNotFound("Failed : HTTP error code : " + response.getStatus());
        }
        String output = response.getEntity(String.class);
        JSONObject result = (JSONObject) jsonParser.parse(output);
        return result;
    }
}

