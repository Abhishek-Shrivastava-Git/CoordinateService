package com.find.coordinate.FindCoordinate.service;


import com.find.coordinate.FindCoordinate.dto.CoordinateDTO;
import com.find.coordinate.FindCoordinate.dto.RequestDTO;

public interface IPostCodeService {
    CoordinateDTO findTheCoordinates(RequestDTO reqdto);
}
