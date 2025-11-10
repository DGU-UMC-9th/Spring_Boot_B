package com.example.umc9th.domain.member.entity.mapping;
import com.example.umc9th.domain.member.entity.Food;
import com.example.umc9th.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member_food")

//다대다 관계는 테이블을 따로 만들어준다.
//일대다 관계는 상황에 따라 다르다.

public class MemberFood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //ManyToOne은 1:N 관계에서 내가 1쪽임을 의미한다.
    //JoinColumn은 외보에서 조인해온 속성을 의미한다.
    @ManyToOne(fetch = FetchType.LAZY)
    //LAZY는 지연로딩, EAGER은 즉시로딩
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id")
    private Food food;

}
