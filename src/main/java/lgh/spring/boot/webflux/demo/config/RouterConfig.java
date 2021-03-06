package lgh.spring.boot.webflux.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import lgh.spring.boot.webflux.demo.handler.TimerHandler;

@Configuration
public class RouterConfig {
	@Autowired
	private TimerHandler timerHandler;

	@Bean
	public RouterFunction<ServerResponse> appRouter() {
		return RouterFunctions.route(RequestPredicates.GET("/time"), timerHandler::getTime)
				.andRoute(RequestPredicates.GET("/date"), timerHandler::getDate)
				.andRoute(RequestPredicates.GET("/times"), timerHandler::getTimePerSec);
	}
}
