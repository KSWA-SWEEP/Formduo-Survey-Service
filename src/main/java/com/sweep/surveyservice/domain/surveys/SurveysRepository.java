package com.sweep.surveyservice.domain.surveys;

//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SurveysRepository extends MongoRepository<Surveys, String> {
//    List<Surveys> findAllByEmail(String email);

//    @Query(" select s from Surveys s where s.delYn = 'N' and s.email = ?1 ")
//    public List<Surveys> findAllByEmail(String email);

    @Query(" {'delYn': 'N', 'email': ?0} ")
    public List<Surveys> findAllByEmail(String email);
//
//    @Query(" select s from Surveys s where s.delYn = 'N' and s.email = ?1 and s.svyType= ?2")

    @Query(" {'delYn':  'N', 'email': ?1, 'svyType': ?2}")
    public List<Surveys> findAllByEmailAndSvyType(String email, String type);
}