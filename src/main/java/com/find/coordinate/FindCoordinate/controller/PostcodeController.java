package com.find.coordinate.FindCoordinate.controller;

import com.find.coordinate.FindCoordinate.dto.CoordinateDTO;
import com.find.coordinate.FindCoordinate.dto.RequestDTO;
import com.find.coordinate.FindCoordinate.service.IPostCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class PostcodeController {

    @Autowired
    IPostCodeService postcodeService;


    @PostMapping("/coordinates")
    public ResponseEntity<?> getTheCoordinates(@RequestBody RequestDTO reqDto){

         CoordinateDTO cdt=postcodeService.findTheCoordinates(reqDto);

        HashMap<String,String> reponseMessage= new HashMap<>();
         if(cdt!=null){
             reponseMessage.put("message","Congratulations " +   reqDto.getName() + ". Lat: "+cdt.getLatitude()+ ", long: "+ cdt.getLongitude());
             return new ResponseEntity<>(reponseMessage,HttpStatus.OK);
         }else {
             reponseMessage.put("message","Sorry " +   reqDto.getName() + ", we haven't found such postcode.");
             return new ResponseEntity<>(reponseMessage, HttpStatus.NOT_FOUND);
         }

    }
}
