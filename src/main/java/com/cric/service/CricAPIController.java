package com.cric.service;

import com.cric.score.CricAPIResponse;
import com.cric.score.CricAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CricAPIController {

    @Autowired
    CricAPIService CricApiService;

    @GetMapping("/score/{uniqueId}")
    public CricAPIResponse sendResponse(@PathVariable Integer uniqueId){
        CricAPIResponse response = CricApiService.processUniqueId(uniqueId);
        return response;
    }


}
