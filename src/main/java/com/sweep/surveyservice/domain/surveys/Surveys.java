package com.sweep.surveyservice.domain.surveys;

import com.vladmihalcea.hibernate.type.json.JsonType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@NoArgsConstructor
//@Entity
@TypeDef(name = "json", typeClass = JsonType.class)
@Document(collection = "surveys")
//@Table(name = "TB_SVY")
public class Surveys {

//    private Instant current = Instant.now();
    @Id
//    @GeneratedValue(strategy=GenerationType.IDENTITY)
//    @Column(name = "SVY_ID", nullable = false)
//    @Field("SVY_ID")
    private String id;

//    @Column(name = "REG_USER", nullable = false)
    @Field("REG_USER")
    private String email;

//    @Column(name = "SVY_ST", nullable = false)
    @Field("SVY_ST")
    private String svySt;

//    @Column(name = "SVY_TITLE", nullable = false)
    @Field("SVY_TITLE")
    private String svyTitle;

    @Lob
//    @Column(name = "SVY_INTRO")
    @Field("SVY_INTRO")
    private String svyIntro;

    @Type(type = "json")
//    @Column(name = "SVY_CONTENT",nullable = false, columnDefinition = "json")
    @Field("SVY_CONTENT")
    private List<Map<String, Object>> svyContent;

//    @Column(name = "SVY_START_DT")
    @Field("SVY_START_DT")
    private Instant svyStartDt;

//    @Column(name = "SVY_END_DT")
    @Field("SVY_END_DT")
    private Instant svyEndDt;

//    @Column(name = "DEL_YN")
    @Field("DEL_YN")
    private Character delYn;

//    @Column(name = "REG_DT")
    @Field("REG_DT")
    private Instant regDt;

//    @Column(name = "UPD_DT")
    @Field("UPD_DT")
    private Instant updDt;

//    @Column(name = "SVY_END_MSG")
    @Field("SVY_END_MSG")
    private String svyEndMsg;

//    @Column(name = "SVY_RESP_MAX")
    @Field("SVY_RESP_MAX")
    private Integer svyRespMax;

//    @Column(name = "SVY_RESP_COUNT")
    @Field("SVY_RESP_COUNT")
    private Integer svyRespCount;

//    @Column(name = "SVY_TYPE")
    @Field("SVY_TYPE")
    private String svyType;

    @Builder
    public Surveys(String svySt, String email, String svyTitle,
                   String svyIntro, List<Map<String, Object>> svyContent, char delYn,
                   String svyEndMsg, int svyRespCount, int svyRespMax,
                   Instant regDt, Instant svyEndDt, Instant svyStartDt,
                   Instant updDt, String svyType){
        this.svySt = svySt;
        this.email = email;
        this.svyTitle = svyTitle;
        this.svyIntro = svyIntro;
        this.svyContent = svyContent;
        this.delYn = delYn;
        this.svyEndMsg = svyEndMsg;
        this.regDt = regDt;
        this.svyStartDt = svyStartDt;
        this.svyEndDt = svyEndDt;
        this.svyType = svyType;
        this.svyRespMax = svyRespMax;
        this.svyRespCount = svyRespCount;
        this.updDt = updDt;
    }

    public void update(String svyTitle, String svyIntro, List<Map<String, Object>> svyContent, Instant svyStartDt,
                       Instant svyEndDt, Instant updDt, String svyEndMsg,
                       int svyRespCount, int svyRespMax){

        this.svyTitle = svyTitle;
        this.svyIntro = svyIntro;
        this.svyContent = svyContent;
        this.svyStartDt = svyStartDt;
        this.svyEndDt = svyEndDt;
        this.svyEndMsg = svyEndMsg;
        this.svyRespCount = svyRespCount;
        this.svyRespMax = svyRespMax;
        this.updDt = updDt;
    }

    public void remove() {
        this.delYn = 'Y';
    }

    public void countUp(int svyRespCount) {
        this.svyRespCount = svyRespCount;}

}