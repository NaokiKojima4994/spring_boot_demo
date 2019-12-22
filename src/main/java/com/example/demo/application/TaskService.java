package com.example.demo.application;

import com.example.demo.application.exception.CustomException;
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

    public void run() throws InterruptedException, ExecutionException, Exception {
        System.out.println("## TaskService::run start. ##");

        CompletableFuture<Integer> one = asyncService.getValueAsync(1, 2000)
                .thenApplyAsync(r -> r + 100)
                .handleAsync((r, e) -> {
                    if (e != null) {
                        return 0;
                    } else {
                        return r;
                    }
                });
                one.thenAcceptAsync(r -> System.out.println("thread tasks end of value => " + r));

        CompletableFuture<Integer> two = asyncService.getValueAsync(3, 3000)
                .thenApplyAsync(r -> r + 100)
                .handleAsync((r, e) -> {
                    if (e != null) {
                        return 0;
                    } else {
                        return r;
                    }
                });
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
