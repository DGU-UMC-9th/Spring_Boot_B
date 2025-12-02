package com.example.umc_9th.domain.review.repository;

import com.example.umc_9th.domain.member.entity.Member;
import com.example.umc_9th.domain.review.entity.Review;
import com.example.umc_9th.domain.store.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewRepositoryCustom {
    Page<Review> findAllByStore(Store store, Pageable pageable);
    Page<Review> findAllByMember(Member member, Pageable pageable);
}
