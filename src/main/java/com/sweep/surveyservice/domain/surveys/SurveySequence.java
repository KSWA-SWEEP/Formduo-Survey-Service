package com.sweep.surveyservice.domain.surveys;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@RequiredArgsConstructor
@Data
@Document(collection = "database_sequences")
public class SurveySequence {

    @Id
    private String id;

    private long seq;
}