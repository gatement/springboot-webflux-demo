package lgh.spring.boot.webflux.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.MongoOperations;

import lgh.spring.boot.webflux.demo.entity.MyEvent;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

    @Bean
    public CommandLineRunner initData(MongoOperations mongo) {
        return (String... args) -> {
            mongo.dropCollection(MyEvent.class);
            mongo.createCollection(MyEvent.class, CollectionOptions.empty().maxDocuments(200).size(100000).capped());
        };
    }
}
