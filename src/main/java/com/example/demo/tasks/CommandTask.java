package com.example.demo.tasks;

import com.example.demo.application.TaskService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandTask implements CommandLineRunner {

    private TaskService taskService;

    public CommandTask(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public void run(String... args) {
        System.out.println("## CommandTask::run start. ##");
        taskService.run();
        System.out.println("## CommandTask::run end. ##");
        System.exit(0);
    }
}
