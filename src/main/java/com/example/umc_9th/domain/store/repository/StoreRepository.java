package com.example.umc_9th.domain.store.repository;

import com.example.umc_9th.domain.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
    // JpaRepository를 상속받으면 findById 등 기본 메서드가 자동으로 생성됩니다.
}