package com.example.umc9th.domain.test.controller;

import com.example.umc9th.domain.test.service.ForLoopVsStreamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test/performance")
public class ForLoopVsStreamController {

    private final ForLoopVsStreamService performanceService;

    @GetMapping("/compare")
    public String compare(@RequestParam(defaultValue = "1000000") int size) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            numbers.add(i);
        }

        StringBuilder result = new StringBuilder();
        result.append(String.format("데이터 크기: %d개\n\n", size));

        // 1. Sum 비교
        long start = System.nanoTime();
        performanceService.sumByForLoop(numbers);
        long forLoopSumTime = System.nanoTime() - start;

        start = System.nanoTime();
        performanceService.sumByStream(numbers);
        long streamSumTime = System.nanoTime() - start;

        result.append("[Sum 연산]\n");
        result.append(String.format("For Loop: %d ns\n", forLoopSumTime));
        result.append(String.format("Stream:   %d ns\n", streamSumTime));
        result.append(String.format("더 빠른 방식: %s\n\n", forLoopSumTime < streamSumTime ? "For Loop" : "Stream"));

        // 2. Filter 비교
        start = System.nanoTime();
        performanceService.filterByForLoop(numbers);
        long forLoopFilterTime = System.nanoTime() - start;

        start = System.nanoTime();
        performanceService.filterByStream(numbers);
        long streamFilterTime = System.nanoTime() - start;

        result.append("[Filter 연산]\n");
        result.append(String.format("For Loop: %d ns\n", forLoopFilterTime));
        result.append(String.format("Stream:   %d ns\n", streamFilterTime));
        result.append(String.format("더 빠른 방식: %s\n", forLoopFilterTime < streamFilterTime ? "For Loop" : "Stream"));

        return result.toString();
    }
}

