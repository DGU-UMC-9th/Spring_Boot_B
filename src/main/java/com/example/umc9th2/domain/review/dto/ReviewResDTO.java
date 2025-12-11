package com.example.umc9th2.domain.review.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;
// 9주차 Request Body에 해당
//가독성을 위해 PreViewDTO도 따로 만들지 않고 한 곳에 모아둠.

//record 데이터 타입은 불변 데이터를 관리하기 위한 클래스의 일종.
//record로 안하고 static class로 만들어도 되긴함.

public class ReviewResDTO {

    //리뷰들의 정보 리스트
    @Builder
    public record ReviewPreViewListDTO(
            List<ReviewPreViewDTO> reviewList, //리뷰의 정보를 담은 DTO의 리스트
            Integer listSize, //페이지 당 사이즈
            Integer totalPage, //전체 페이지
            Long totalElements, //전체 원소 수
            Boolean isFirst, //첫 페이지
            Boolean isLast //마지막 페이지
    ){}

    //리뷰의 정보를 담은 DTO
    @Builder
    public record ReviewPreViewDTO(
            String ownerNickname,
            Float score,
            String body,
            LocalDate createdAt
    ){}
}
