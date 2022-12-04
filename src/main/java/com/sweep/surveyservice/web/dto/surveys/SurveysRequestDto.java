package com.sweep.surveyservice.web.dto.surveys;

import com.sweep.surveyservice.domain.surveys.Surveys;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

/**
 * A DTO for the {@link Surveys} entity
 */
@Getter
@NoArgsConstructor
public class SurveysRequestDto {

    private String svySt;
    private String svyTitle;
    private String svyIntro;
    private List<Map<String, Object>> svyContent;
    private Instant svyStartDt;
    private Instant svyEndDt;
    private String svyEndMsg;
    private Integer svyRespMax;
    private Integer svyRespCount;
    private String svyType= "";

    // 클라이언트가 요청할때 필요한 정보들입니다~.

    @Builder
    public SurveysRequestDto(String svyTitle, String svyIntro, List<Map<String, Object>> svyContent, String svyType,
                             String svyEndMsg, String svySt, Instant svyStartDt, Instant svyEndDt, int svyRespMax, int svyRespCount){

        this.svySt = svySt;
        this.svyTitle = svyTitle;
        this.svyIntro = svyIntro;
        this.svyContent = svyContent;
        this.svyEndMsg = svyEndMsg;
        this.svyStartDt = svyStartDt;
        this.svyEndDt = svyEndDt;
        this.svyRespMax = svyRespMax;
        this.svyRespCount = svyRespCount;
        this.svyType = svyType;
    }

    public Surveys toEntity(String email) {
        return Surveys.builder()
                .svySt(svySt)
                .email(email)
                .svyTitle(svyTitle)
                .svyIntro(svyIntro)
                .svyContent(svyContent)
                .svyEndMsg(svyEndMsg)
                .regDt(Instant.now().plus(9, ChronoUnit.HOURS))
                .updDt(Instant.now().plus(9, ChronoUnit.HOURS))
                .delYn('N')
                .svyStartDt(svyStartDt.plus(9, ChronoUnit.HOURS))
                .svyEndDt(svyEndDt.plus(9, ChronoUnit.HOURS))
                .svyRespCount(svyRespCount)
                .svyRespMax(svyRespMax)
                .svyType(svyType)
                .build();
    }
}