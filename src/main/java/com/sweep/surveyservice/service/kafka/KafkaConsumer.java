package com.sweep.surveyservice.service.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sweep.surveyservice.domain.surveys.Surveys;
import com.sweep.surveyservice.domain.surveys.SurveysRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.core.support.IncompleteRepositoryCompositionException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class KafkaConsumer {

    SurveysRepository repository;

    @Autowired
    public KafkaConsumer(SurveysRepository repository) {
        this.repository = repository;
    }

    @KafkaListener(topics = "survey-response-topic")
    public void updateQty(String kafkaMessage){ //토픽에서 메세지 가져옴
        log.info("Kafka Message: ->" + kafkaMessage);

        Map<Object, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            map = mapper.readValue(kafkaMessage, new TypeReference<Map<Object, Object>>() {}); // Json 형태의 string -> json
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }

        int surveyId = (Integer) map.get("surveyId");
        Surveys entity = repository.findById(surveyId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다. id =" + surveyId));
        if (entity != null) {
            entity.countUp(entity.getSvyRespCount() + 1);
            repository.save(entity);
        }

    }
}