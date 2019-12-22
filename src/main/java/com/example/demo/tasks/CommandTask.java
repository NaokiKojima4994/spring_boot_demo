package com.example.demo.tasks;

import com.example.demo.application.TaskService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Component
public class CommandTask implements CommandLineRunner {

    private TaskService taskService;

    public CommandTask(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public void run(String... args) throws ExecutionException, InterruptedException, Exception {
        long start = System.currentTimeMillis();

        System.out.println("## CommandTask::run start. ##");
        taskService.run();
        System.out.println("## CommandTask::run end. ##");

        long end = System.currentTimeMillis();
        System.out.println((end - start)  + "ms");
        System.exit(0);
    }
}
