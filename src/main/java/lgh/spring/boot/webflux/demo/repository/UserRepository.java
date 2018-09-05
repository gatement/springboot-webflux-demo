package lgh.spring.boot.webflux.demo.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import lgh.spring.boot.webflux.demo.entity.User;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveCrudRepository<User, String> {
    Mono<User> findByUsername(String username);
    Mono<Long> deleteByUsername(String username);
}