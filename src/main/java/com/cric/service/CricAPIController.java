package com.cric.service;

import com.cric.ApiError;
import com.cric.EntityNotFound;
import com.cric.score.CricAPIResponse;
import com.cric.score.CricAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CricAPIController {

    @Autowired
    CricAPIService CricApiService;

    @GetMapping("/score/{uniqueId}")
    public ResponseEntity sendResponse(@PathVariable Integer uniqueId){
        if(uniqueId == null){
            return ResponseEntity.badRequest().body(null);
        }
        try {
            CricAPIResponse response = CricApiService.processUniqueId(uniqueId);
            return ResponseEntity.ok(response);
        }catch (EntityNotFound e){
            return ResponseEntity.badRequest().body(new ApiError(HttpStatus.BAD_REQUEST,"Unable to find the Entry in cric api",e));
        }
    }


}
