package lgh.spring.boot.webflux.demo;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.reactive.function.client.WebClient;

import lgh.spring.boot.webflux.demo.entity.MyEvent;
import lgh.spring.boot.webflux.demo.entity.User;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
// run tests after the the server is started
public class ApplicationTests {

	@Test
	public void contextLoads() {
	}

	//@Test
	public void webClientTest1() throws InterruptedException {
		WebClient webClient = WebClient.create("http://localhost:8080");
		Mono<String> resp = webClient.get().uri("/user")
				.retrieve()
				.bodyToMono(String.class);
		resp.subscribe(System.out::println);
		TimeUnit.SECONDS.sleep(5);
	}

    //@Test
    public void webClientTest2() throws InterruptedException {
        WebClient webClient = WebClient.builder().baseUrl("http://localhost:8080").build();
        webClient
                .get().uri("/user")
                .accept(MediaType.APPLICATION_STREAM_JSON)
                .exchange()
                .flatMapMany(response -> response.bodyToFlux(User.class))
                .doOnNext(System.out::println)
                .blockLast();
    }
    
    //@Test
    public void webClientTest3() throws InterruptedException {
        WebClient webClient = WebClient.create("http://localhost:8080");
        webClient
                .get().uri("/times")
                .accept(MediaType.TEXT_EVENT_STREAM)
                .retrieve()
                .bodyToFlux(String.class)
                .log() 
                .take(10) 
                .blockLast();
    }

    @Test
    public void webClientTest4() {
        Flux<MyEvent> eventFlux = Flux.interval(Duration.ofSeconds(1))
                .map(l -> new MyEvent(System.currentTimeMillis(), "message-" + l)).take(5);
        WebClient webClient = WebClient.create("http://localhost:8080");
        webClient
                .post().uri("/events")
                .contentType(MediaType.APPLICATION_STREAM_JSON)
                .body(eventFlux, MyEvent.class) 
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }
}
