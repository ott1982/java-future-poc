package com.example.demo.infrastructure;

import com.example.demo.domain.WrappedApiCaller;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class WrappedApiCallerImpl implements WrappedApiCaller {

    private final ExecutorService executorService;

    public WrappedApiCallerImpl(ExecutorService executorService) {
        this.executorService = executorService;
    }

    @Override
    public Future<String> delayAndReturnOk(Long millis) {
        return executorService.submit(() -> {
            try {
                Thread.sleep(millis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "OK";
        });
    }

    @Override
    public Future<String> delayAndThrowRuntimeExection(Long millis, RuntimeException runtimeException) {
        return executorService.submit(() -> {
            try {
                Thread.sleep(millis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            throw runtimeException;
        });
    }


}
