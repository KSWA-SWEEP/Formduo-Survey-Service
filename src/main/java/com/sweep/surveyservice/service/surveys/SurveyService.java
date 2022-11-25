package com.sweep.surveyservice.service.surveys;

import com.sweep.surveyservice.domain.surveys.Surveys;
import com.sweep.surveyservice.domain.surveys.SurveysRepository;
import com.sweep.surveyservice.web.dto.surveys.SurveysRequestDto;
import com.sweep.surveyservice.web.dto.surveys.SurveysResponseDto;
import com.sweep.surveyservice.web.dto.surveys.SurveysUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.asm.Advice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class SurveyService {
    private final SurveysRepository surveysRepository;
    @Transactional
    public Integer save(SurveysRequestDto requestDto, String email) {
//        Members members = memberRepository.findByEmail(SecurityUtil.getCurrentMemberEmail())
//                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다. " + SecurityUtil.getCurrentMemberEmail()));
        //설문 저장
        return surveysRepository.save(requestDto.toEntity(email)).getId();
    }

    @Transactional
    public Integer update(int id, SurveysUpdateRequestDto requestDto){
        Surveys surveys = surveysRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다. id =" + id));

        surveys.update(requestDto.getSvyTitle(),
                requestDto.getSvyIntro(),
                requestDto.getSvyContent(),
                requestDto.getSvyStartDt(),
                requestDto.getSvyEndDt(),
                requestDto.getUpdDt(),
                requestDto.getSvyEndMsg(),
                requestDto.getSvyRespCount(),
                requestDto.getSvyRespMax());
        return id;
    }

    @Transactional(readOnly = true)
    public SurveysResponseDto findById(int id){
        Surveys entity = surveysRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 설문이 없습니다. id ="+ id));

        System.out.println(entity);
        return new SurveysResponseDto(entity);}

    @Transactional(readOnly = true)
    public List<SurveysResponseDto> findByType(String type, String email){

        List<Surveys> list = surveysRepository.findAllByEmailAndSvyType(email, type);

//        if(list.isEmpty()) throw new IllegalArgumentException("해당 타입의 설문이 없습니다. "+ type);

        return list.stream().map(SurveysResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public String remove(int id){
        Surveys entity = surveysRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 설문이 없습니다. id ="+ id));

//        surveysRepository.deleteById(id);
        entity.remove();
        return entity.getSvyTitle();}


    @Transactional(readOnly = true)
    public List<SurveysResponseDto> findAll(String email) {
        // 설문이 있는지 없는지 확인

        List<Surveys> list = surveysRepository.findAllByEmail(email);

//        if(list.isEmpty()) throw new IllegalArgumentException("생성된 설문이 없습니다. ");
        // 소팅 조건
//        Sort sort = Sort.by(Sort.Direction.DESC, "id", "regDt");
//        List<Surveys> list = surveysRepository.findAll(sort);

        return list.stream().map(SurveysResponseDto::new).collect(Collectors.toList());
    }
}
