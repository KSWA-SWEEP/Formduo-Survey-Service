package com.sweep.surveyservice.web.controller;


import com.sweep.surveyservice.service.qbox.QboxService;
import com.sweep.surveyservice.service.surveys.SurveyService;
import com.sweep.surveyservice.web.dto.qbox.QboxRequestDto;
import com.sweep.surveyservice.web.dto.qbox.QboxResponseDto;
import com.sweep.surveyservice.web.dto.surveys.SurveysRequestDto;
import com.sweep.surveyservice.web.dto.surveys.SurveysResponseDto;
import com.sweep.surveyservice.web.dto.surveys.SurveysUpdateRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Tag(name = "설문", description = "설문 관련 api 입니다.")
@RequiredArgsConstructor
@RequestMapping("/api/v1/surveys")
@RestController
public class SurveysApiController {

    private final SurveyService surveyService;
    private final QboxService qBoxService;

    @Operation(summary = "설문 생성 요청", description = "설문이 생성됩니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = SurveysResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping("")
    public int save(@RequestBody SurveysRequestDto requestDto, @RequestHeader("X-Authorization-Id") String email) {
        System.out.println(email);
        return surveyService.save(requestDto, email);
    }

    @Operation(summary = "설문 수정 요청", description = "설문이 수정됩니다.")
    @PutMapping("{id}")
    public int update(@PathVariable Integer id, @RequestBody SurveysUpdateRequestDto requestDto){
        return surveyService.update(id, requestDto);
    }

    @Operation(summary = "설문 조회 요청", description = "해당 아이디의 설문이 조회됩니다.")
    @GetMapping("{id}")
    public SurveysResponseDto findSurveyById (@PathVariable Integer id) {
        return surveyService.findById(id);
    }

    @Operation(summary = "설문 삭제 요청", description = "해당 아이디의 설문이 삭제됩니다.")
    @DeleteMapping("{id}")
    public String remove (@PathVariable Integer id) {
        return surveyService.remove(id);
    }

    @Operation(summary = "자신의 모든 설문 요청", description = "해당 아이디의 모든 설문이 조회됩니다.")
    @GetMapping("")
    public List<SurveysResponseDto> getAllMySurveys (@RequestHeader("X-Authorization-Id") String email) {
        return surveyService.findAll(email);
    }

    @Operation(summary = "설문 종류별 요청", description = "해당 종류의 내가 만든 설문 전체를 조회합니다.")
    @GetMapping("type")
    public List<SurveysResponseDto> getByTypeMySurveys (@RequestParam String type, @RequestHeader("X-Authorization-Id") String email) {
        return surveyService.findByType(type, email);
    }

    @Operation(summary = "Q-BOX 생성 요청", description = "Q-BOX 생성을 요청합니다.")
    @PostMapping("/qbox")
    public int save(@RequestBody QboxRequestDto requestDto) {
        return qBoxService.save(requestDto);
    }

//    @Operation(summary = "Qbox 수정 요청", description = "Qbox id를 이용하여 내용 수정을 요청합니다.")
//    @PutMapping("/{id}")
//    public Long update(@PathVariable int id, @RequestBody PostsUpdateRequestDto requestDto){
//        return qBoxService.update(id, requestDto);
//    }

    @Operation(summary = "Q-BOX 조회 요청", description = "Q-BOX id를 이용하여 내용 조회를 요청합니다.")
    @GetMapping("/qbox/{id}")
    public QboxResponseDto findById (@PathVariable int id) {
        return qBoxService.findById(id);
    }

    @Operation(summary = "Q-BOX 전체 조회 요청", description = "Q-BOX 전체 내용 조회를 요청합니다.")
    @GetMapping("/qbox")
    public List<QboxResponseDto> findAll () {
        return qBoxService.findAll();
    }

}
