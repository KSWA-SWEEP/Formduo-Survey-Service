package com.sweep.surveyservice.domain.qbox;

import com.vladmihalcea.hibernate.type.json.JsonType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;
import java.util.Map;

@Getter
@NoArgsConstructor
//@Entity
@TypeDef(name = "json", typeClass = JsonType.class)
@Document(collection = "qbox")
//@Table(name = "TB_QBOX")
public class Qbox {

//    private Instant current = Instant.now();

    @Id
//    @GeneratedValue(strategy=GenerationType.IDENTITY)
//    @Column(name = "q_id", nullable = false)
//    @Field("q_id")
    private String id;

//    @Column(name = "q_title", nullable = false)
    @Field("q_title")
    private String qTitle;

//    @Column(name = "q_info", nullable = false)
    @Field("q_info")
    private String qInfo;

//    @Column(name = "name", nullable = false)
    @Field("name")
    private String name;

//    @Column(name = "q_image")
    @Field("q_image")
    private String qImage;

//    @Column(name = "q_video")
    @Field("q_video")
    private String qVideo;

//    @Column(name = "q_multi")
    @Field("q_multi")
    private String qMulti;

    @Type(type = "json")
//    @Column(name = "q_contents",nullable = false, columnDefinition = "json")
    @Field("q_contents")
    private List<Map<String, Object>> qContents;

//    @Column(name = "CONTENT_YN")
    @Field("CONTENT_YN")
    private Character contentYn;
//    @Column(name = "DEL_YN")
    @Field("DEL_YN")
    private Character delYn;

//    @Column(name = "REG_DT")
    @Field("REG_DT")
    private Instant regDt;

//    @Column(name = "UPD_DT")
    @Field("UPD_DT")
    private Instant updDt;


    @Builder
    public Qbox(String qTitle, String qInfo, String name,
                String qImage, String qVideo, String qMulti, List<Map<String, Object>> qContents,
                Character contentYn, Character delYn, Instant regDt, Instant updDt){

        this.qTitle = qTitle;
        this.qInfo = qInfo;
        this.name = name;
        this.qImage = qImage;
        this.qVideo = qVideo;
        this.qMulti = qMulti;
        this.qContents = qContents;
        this.contentYn = contentYn;
        this.delYn = delYn;
        this.regDt = regDt;
        this.updDt = updDt;
    }

    public void update(String qTitle, String qInfo, String name,
                       String qImage, String qVideo, String qMulti, List<Map<String, Object>> qContents,
                       Character contentYn, Instant updDt){

        this.qTitle = qTitle;
        this.qInfo = qInfo;
        this.name = name;
        this.qImage = qImage;
        this.qVideo = qVideo;
        this.qMulti = qMulti;
        this.qContents = qContents;
        this.contentYn = contentYn;
        this.updDt = updDt;
    }

    public void remove() {
        this.delYn = 'Y';
    }

//    public void countUp(int svyRespCount) {
//        this.svyRespCount = svyRespCount;}

}