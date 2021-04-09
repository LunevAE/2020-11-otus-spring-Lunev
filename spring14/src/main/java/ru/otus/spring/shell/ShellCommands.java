package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@RequiredArgsConstructor
@ShellComponent
public class ShellCommands {

    private final Job importBookJob;

    private final JobLauncher jobLauncher;

    @ShellMethod(value = "startMigrationJobFromH2ToMongo", key = "sj")
    public void startMigrationJobFromH2ToMongo() throws Exception {
        JobExecution execution = jobLauncher.run(importBookJob, new JobParameters());
    }
}

