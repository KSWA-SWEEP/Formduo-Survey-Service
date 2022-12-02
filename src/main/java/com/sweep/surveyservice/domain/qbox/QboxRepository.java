package com.sweep.surveyservice.domain.qbox;

//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QboxRepository extends MongoRepository<Qbox, String> {
//    List<Surveys> findAllByEmail(String email);


}