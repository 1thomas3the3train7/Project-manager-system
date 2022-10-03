package com.example.authservice.Repository;

import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class RedisRepository {
    private final ReactiveRedisOperations<String,String> reactiveRedisOperations;

    public RedisRepository(ReactiveRedisOperations<String,String> reactiveRedisOperations) {
        this.reactiveRedisOperations = reactiveRedisOperations;
    }
    public Mono<Boolean> save(final String key,final String value){
        return reactiveRedisOperations.opsForValue().set(key,value);
    }
    public Mono<String> get(final String key){
        return reactiveRedisOperations.opsForValue().get(key);
    }
}
