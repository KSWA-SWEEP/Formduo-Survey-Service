package com.sweep.surveyservice.service.qbox;

import com.sweep.surveyservice.domain.qbox.Qbox;
import com.sweep.surveyservice.domain.qbox.QboxRepository;
import com.sweep.surveyservice.web.dto.qbox.QboxRequestDto;
import com.sweep.surveyservice.web.dto.qbox.QboxResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class QboxService {

    private final QboxRepository qboxRepository;


    @Transactional
    public String save(QboxRequestDto requestDto) {
        return qboxRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional(readOnly = true)
    public QboxResponseDto findById(String id){
        Qbox entity = qboxRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 큐박스 컨텐츠가 없습니다. id ="+ id));

        return new QboxResponseDto(entity);}

    @Transactional(readOnly = true)
    public List<QboxResponseDto> findAll() {
        // 설문이 있는지 없는지 확인
        List<Qbox> list = qboxRepository.findAll();
        // 소팅 조건
//        Sort sort = Sort.by(Sort.Direction.DESC, "id", "regDt");
//        List<Surveys> list = surveysRepository.findAll(sort);

        return list.stream().map(QboxResponseDto::new).collect(Collectors.toList());
    }
}
