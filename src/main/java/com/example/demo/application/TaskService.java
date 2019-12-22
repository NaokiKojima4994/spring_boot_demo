package com.example.demo.application;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@EnableAsync
public class TaskService {

    public Integer getValue(Integer id) {
        return id;
    }

    @Async
    public CompletableFuture<Integer> getValueAsync(Integer id) throws InterruptedException {
        Random rnd = new Random();
        Integer sleepTime = rnd.nextInt(1000);
        Thread.sleep(sleepTime);
        System.out.println("Sleep time is " + sleepTime);
        return CompletableFuture.completedFuture(getValue(id));
    }

    public void run() throws InterruptedException, ExecutionException {
        System.out.println("## TaskService::run start. ##");

        List<CompletableFuture<Integer>> outputs = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            CompletableFuture<Integer> result = getValueAsync(i)
                    .thenApply(r ->  r + 100);
            outputs.add(result);
        }

        CompletableFuture.allOf(outputs.toArray(new CompletableFuture[0])).join();

        for (CompletableFuture<Integer> task: outputs) {
            Integer value = task.get();
            System.out.println(value);
        }

        System.out.println("## TaskService::run end.  ##");
    }
}
