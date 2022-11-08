package com.example.demo.infrastructure;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WrappedApiCallerImplTest {

    WrappedApiCallerImpl sut;

    @BeforeEach
    void setUp() {
        sut = new WrappedApiCallerImpl(Executors.newSingleThreadExecutor());
    }

    @Test
    void delayAndReturnOk() throws Exception {
        Future<String> future = sut.delayAndReturnOk(10000L);
        Assertions.assertThat(future.isDone()).isFalse();
        Assertions.assertThat(future.get()).isEqualTo("OK");
        Assertions.assertThat(future.isDone()).isTrue();
    }

    @Test
    void cancelDelayAndReturnOk() throws Exception {
        Future<String> future = sut.delayAndReturnOk(10000L);
        future.cancel(true);
        Assertions.assertThat(future.isCancelled()).isTrue();
        Assertions.assertThatThrownBy(() -> future.get()).isInstanceOf(CancellationException.class);
    }

    @Test
    void delayAndReturnOkButTimeoutOnRetrieve() throws Exception {
        Future<String> future = sut.delayAndReturnOk(10000L);
        Assertions.assertThatThrownBy(() -> future.get(5000L, TimeUnit.MILLISECONDS))
                .isInstanceOf(TimeoutException.class);
    }

    @Test
    void delayAndThrowRuntimeExection() throws Exception {
        Future<String> future = sut.delayAndThrowRuntimeExection(10000L, new RuntimeException("runtime exception!"));
        Assertions.assertThatThrownBy(() -> future.get()).isInstanceOf(ExecutionException.class)
                .hasCauseInstanceOf(RuntimeException.class);
    }
}