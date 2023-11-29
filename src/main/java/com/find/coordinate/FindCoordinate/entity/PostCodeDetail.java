package com.find.coordinate.FindCoordinate.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
@Data
public class PostCodeDetail {

    @Id
    private String postcode;
    private String longitude;
    private String latitude;

}
