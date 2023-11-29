package com.find.coordinate.FindCoordinate.respository;

import com.find.coordinate.FindCoordinate.entity.PostCodeDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostcodeRepository extends JpaRepository<PostCodeDetail,String> {

}
