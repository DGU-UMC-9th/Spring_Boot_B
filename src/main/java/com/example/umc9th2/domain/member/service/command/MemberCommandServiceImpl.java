package com.example.umc9th2.domain.member.service.command;

import com.example.umc9th2.domain.member.converter.MemberConverter;
import com.example.umc9th2.domain.member.dto.MemberReqDTO;
import com.example.umc9th2.domain.member.dto.MemberResDTO;
import com.example.umc9th2.domain.member.entity.Food;
import com.example.umc9th2.domain.member.entity.Member;
import com.example.umc9th2.domain.member.entity.mapping.MemberFood;
import com.example.umc9th2.domain.member.exception.FoodException;
import com.example.umc9th2.domain.member.repository.FoodRepository;
import com.example.umc9th2.domain.member.repository.MemberFoodRepository;
import com.example.umc9th2.domain.member.repository.MemberRepository;
import jakarta.transaction.*;
import lombok.*;
import org.springframework.stereotype.*;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberCommandServiceImpl implements MemberCommandService {

    private final MemberRepository memberRepository;
    private final MemberFoodRepository memberFoodRepository;
    private final FoodRepository foodRepository;

    @Override
    public MemberResDTO.JoinDTO signup(
            MemberReqDTO.JoinDTO dto
    ){

        Member member = MemberConverter.toMember(dto);
        memberRepository.save(member);

        // 선호 음식 존재 여부 확인
        if (dto.preferCategory().size() > 1) {
            List<MemberFood> memberFoodList = new ArrayList<>();

            // 선호 음식 ID별 조회
            for (Long id : dto.preferCategory()) {

                // 음식 존재 여부 검증
                Food food = foodRepository.findById(id)
                        .orElseThrow(() -> new FoodException(FoodErrorCode.NOT_FOUND));

                // MemberFood 엔티티 생성 (컨버터 사용해야 함)
                MemberFood memberFood = MemberFood.builder()
                        .member(member)
                        .food(food)
                        .build();

                // 사용자 - 음식 (선호 음식) 추가
                memberFoodList.add(memberFood);
            }

            // 모든 선호 음식 추가: DB 적용
            memberFoodRepository.saveAll(memberFoodList);
        }

        return MemberConverter.toJoinDTO(member);
    }
}
