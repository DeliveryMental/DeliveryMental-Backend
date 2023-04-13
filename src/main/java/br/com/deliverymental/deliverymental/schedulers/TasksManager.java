package br.com.deliverymental.deliverymental.schedulers;

import br.com.deliverymental.deliverymental.schedulers.tasks.SendMailTask;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

@Component
public class TasksManager {

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    @PostConstruct
    public void scheduler() {
        threadPoolTaskScheduler.scheduleWithFixedDelay(new SendMailTask(), 2000);
    }
}
