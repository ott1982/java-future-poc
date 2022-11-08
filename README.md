# Java Future POC

POC based on Java 11 Future implementation.

There are 2 main methods in WrappedApiCallerImpl class:

* delayAndThrowRuntimeExection.
* delayAndReturnOk.

Notice WrappedApiCallerImpl constructor receive an ExecutorService as Argument.

# Use cases

WrappedApiCallerImplTest contains tests for these UC:

1. Working OK: returns the result.
2. Throwing an Exception: throws ExecutionException. Cause will be informed as real Exception when get is called.
3. Working OK but too late. throws TimeoutException when get is called.
4. Process cancelation: throws CancellationException when get is called. Check isCanceled! 