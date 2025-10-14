package com.example.umc9th.domain.inquiry.repository;

import com.example.umc9th.domain.inquiry.entity.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InquiryRepository extends JpaRepository<Inquiry, Long> {
}

