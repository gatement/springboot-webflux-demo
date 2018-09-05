package lgh.spring.boot.webflux.demo.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;

import lgh.spring.boot.webflux.demo.entity.MyEvent;
import reactor.core.publisher.Flux;

public interface MyEventRepository extends ReactiveMongoRepository<MyEvent, Long> {
    @Tailable 
    Flux<MyEvent> findBy(); 
}