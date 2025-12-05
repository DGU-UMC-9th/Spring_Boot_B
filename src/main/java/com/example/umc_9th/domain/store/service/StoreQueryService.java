package com.example.umc_9th.domain.store.service;

import com.example.umc_9th.domain.mission.entity.Mission;
import com.example.umc_9th.domain.mission.repository.MissionRepository;
import com.example.umc_9th.domain.review.entity.Review;
import com.example.umc_9th.domain.review.repository.ReviewRepository;
import com.example.umc_9th.domain.store.entity.Store;
import com.example.umc_9th.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreQueryService {

    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;
    private final MissionRepository missionRepository;

    public Store findStore(Long id) {
        return storeRepository.findById(id).orElseThrow(() -> new RuntimeException("가게를 찾을 수 없습니다."));
    }

    // 리뷰 목록 조회
    public Page<Review> getReviewList(Long storeId, Integer page) {
        Store store = findStore(storeId);

        // page는 0부터 시작하므로, Controller에서 받은 page-1을 할 수도 있고 여기서 할 수도 있습니다.
        // 여기서는 Controller에서 1-based index로 받고, 여기서 0-based로 변환한다고 가정 (page - 1)
        PageRequest pageRequest = PageRequest.of(page, 10); // 페이지 사이즈 10 고정

        return reviewRepository.findAllByStore(store, pageRequest);
    }

    // 미션 목록 조회
    public Page<Mission> getMissionList(Long storeId, Integer page) {
        Store store = findStore(storeId);

        PageRequest pageRequest = PageRequest.of(page, 10);

        return missionRepository.findAllByStore(store, pageRequest);
    }
}