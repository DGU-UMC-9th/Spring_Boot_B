package com.example.umc9th.domain.test.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ForLoopVsStreamService {

    // 1. Sum (합계) - for문
    public long sumByForLoop(List<Integer> numbers) {
        long sum = 0;
        for (Integer number : numbers) {
            sum += number;
        }
        return sum;
    }

    // 1. Sum (합계) - Stream
    public long sumByStream(List<Integer> numbers) {
        return numbers.stream()
                .mapToLong(Integer::longValue)
                .sum();
    }

    // 2. Filter (필터링) - for문
    public List<Integer> filterByForLoop(List<Integer> numbers) {
        List<Integer> result = new ArrayList<>();
        for (Integer number : numbers) {
            if (number % 2 == 0) {
                result.add(number);
            }
        }
        return result;
    }

    // 2. Filter (필터링) - Stream
    public List<Integer> filterByStream(List<Integer> numbers) {
        return numbers.stream()
                .filter(number -> number % 2 == 0)
                .collect(Collectors.toList());
    }
}

