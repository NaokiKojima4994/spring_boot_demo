package com.example.demo.application;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class AsyncService {

    @Async
    public CompletableFuture<Integer> getValueAsync(Integer id, Integer sleepTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException error) {
            System.out.println("error");
        }

        return CompletableFuture.completedFuture(id);
    }
}
