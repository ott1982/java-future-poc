package com.example.demo.domain;

import java.util.concurrent.Future;

public interface WrappedApiCaller {

    Future<String> delayAndReturnOk(Long millis);

    Future<String> delayAndThrowRuntimeExection(Long millis, RuntimeException runtimeException);
}
