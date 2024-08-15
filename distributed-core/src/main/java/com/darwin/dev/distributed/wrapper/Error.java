package com.darwin.dev.distributed.wrapper;

public record Error<T>(
        int statusCode,
        T meta
) {
}
