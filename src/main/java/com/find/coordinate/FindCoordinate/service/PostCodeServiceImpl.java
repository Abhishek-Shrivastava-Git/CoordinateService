package com.find.coordinate.FindCoordinate.service;

import com.find.coordinate.FindCoordinate.dto.CoordinateDTO;
import com.find.coordinate.FindCoordinate.dto.RequestDTO;
import com.find.coordinate.FindCoordinate.dto.ResponseDTO;
import com.find.coordinate.FindCoordinate.entity.PostCodeDetail;
import com.find.coordinate.FindCoordinate.respository.PostcodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class PostCodeServiceImpl implements IPostCodeService {

    @Autowired
    RestTemplate rt;

    @Autowired
    PostcodeRepository postcodeRepository;


    @Override
    public CoordinateDTO findTheCoordinates(RequestDTO reqdto) {
        boolean isDataINsertedinDB=false;
        CoordinateDTO cdDTO=new CoordinateDTO();

        //Fetch Data from DB
        Optional<PostCodeDetail> postCodeDetail = postcodeRepository.findById(reqdto.getPostcode());


         if(postCodeDetail.isPresent()){
             cdDTO.setLatitude(postCodeDetail.get().getLatitude());
             cdDTO.setLongitude(postCodeDetail.get().getLongitude());
             return cdDTO;
         }else{//if Data not found in DB
             String postcode = reqdto.getPostcode();
             try{
                 ResponseEntity<ResponseDTO> resDto = rt.getForEntity("https://api.postcodes.io/postcodes/"+postcode, ResponseDTO.class);
                 if(resDto.getStatusCode().value()==200){
                     cdDTO.setLatitude(resDto.getBody().getResult().getLatitude());
                     cdDTO.setLongitude(resDto.getBody().getResult().getLongitude());
                     //Saving Data in DB
                     isDataINsertedinDB=this.insertDataInsideDatabase(resDto.getBody().getResult(),reqdto.getPostcode());
                     return cdDTO;
                 }

             }catch (HttpClientErrorException e){
                 e.getMessage();
                 e.getStatusCode();
             }
         }
        //Data Not Found
        return null;
    }

    private boolean insertDataInsideDatabase(ResponseDTO.Result newCoordinates,String newPostcode){
        try{
            PostCodeDetail newPostCode=new PostCodeDetail();
            newPostCode.setPostcode(newPostcode);
            newPostCode.setLatitude(newCoordinates.getLatitude());
            newPostCode.setLongitude(newCoordinates.getLongitude());
            postcodeRepository.save(newPostCode);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
