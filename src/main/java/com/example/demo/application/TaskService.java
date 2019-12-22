package com.example.demo.application;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class TaskService {

    private AsyncService asyncService;

    public TaskService(AsyncService asyncService) {
        this.asyncService = asyncService;
    }

    public void run() throws InterruptedException, ExecutionException {
        System.out.println("## TaskService::run start. ##");

        CompletableFuture<Integer> one = asyncService.getValueAsync(1, 2000);
                one.thenAcceptAsync(r -> System.out.println("thread tasks end of value => " + r));

        CompletableFuture<Integer> two = asyncService.getValueAsync(2, 3000);
                two.thenAcceptAsync(r -> System.out.println("thread tasks end of value => " + r));

        System.out.println("待ちスタート");
        CompletableFuture.allOf(one, two).join();
        System.out.println("待ち完了");

        List<Integer> values = new ArrayList<>();
        values.add(one.get());
        values.add(two.get());

        System.out.println(String.valueOf(values));

        System.out.println("## TaskService::run end.  ##");
    }
}
