package lgh.spring.boot.webflux.demo.handler;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

@Component
public class TimerHandler {
	public Mono<ServerResponse> getTime(ServerRequest request) {
		return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
				.body(Mono.just(new SimpleDateFormat("HH:mm:ss").format(new Date())), String.class);
	}

	public Mono<ServerResponse> getDate(ServerRequest request) {
		return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
				.body(Mono.just(new SimpleDateFormat("yyyy-MM-dd").format(new Date())), String.class);
	}
}
