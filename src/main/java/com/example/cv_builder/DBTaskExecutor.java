package com.example.cv_builder;

import javafx.concurrent.Task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class DBTaskExecutor {
    private static final ExecutorService executor = Executors.newCachedThreadPool();

    public static <T> void run(CallableTask<T> callable, Consumer<T> onSuccess, Consumer<Throwable> onFailure) {
        Task<T> task = new Task<>() {
            @Override
            protected T call() throws Exception {
                return callable.call();
            }
        };
        task.setOnSucceeded(e -> onSuccess.accept(task.getValue()));
        task.setOnFailed(e -> onFailure.accept(task.getException()));
        executor.submit(task);
    }

    public interface CallableTask<T> {
        T call() throws Exception;
    }
}