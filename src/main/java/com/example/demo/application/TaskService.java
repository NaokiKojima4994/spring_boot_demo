package com.example.demo.application;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@EnableAsync
public class TaskService {

    @Async
    public CompletableFuture<Integer> getValue(Integer id) throws InterruptedException {
        Thread.sleep(1000);
        return CompletableFuture.completedFuture(id);
    }

    public void run() {
        System.out.println("## TaskService::run start. ##");
        System.out.println("## TaskService::run end.  ##");
    }
}
