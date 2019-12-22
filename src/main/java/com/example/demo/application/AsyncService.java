package com.example.demo.application;

import com.example.demo.application.exception.CustomException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

@Service
public class AsyncService {

    @Async
    public CompletableFuture<Integer> getValueAsync(Integer id, Integer sleepTime) throws CustomException {

        try {
            if (id == 3) {
                throw new Exception("Value error.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));
            return CompletableFuture.completedFuture(id);
        }


        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException error) {
            System.out.println("error");
        }

        return CompletableFuture.completedFuture(id);
    }

    public static Integer handleValue(Integer r, CustomException e) {
        if (e != null) {
            CustomException er = (CustomException) e;
            return er.getValue();
        } else {
            return r;
        }
    }
}
