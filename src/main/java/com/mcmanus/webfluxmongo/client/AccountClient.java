package com.mcmanus.webfluxmongo.client;

import com.mcmanus.webfluxmongo.model.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
public class AccountClient {

    public static void main(String[] args) throws InterruptedException {

        WebClient client = WebClient.create("http://localhost:8080");

   /*     Mono<Account> mono = client.get()
                .uri("/api/account/test")
                .retrieve()
                .bodyToMono(Account.class)
                .doOnSubscribe(subscription -> System.err.println("doOnSubscribe"))
                .log();


        mono.subscribe(System.err::println);
       // mono.subscribe(System.err::println);


        Mono<Account> mono2 = client.get()
                .uri("/api/account/owner/{owner}/first", "Manu")
                .retrieve()
                .bodyToMono(Account.class);

        mono2.subscribe(System.err::println);
*/
        Flux<Account> flux = client.get()
                .uri("/api//account/owner/{owner}", "Manu")
                .retrieve()
                .bodyToFlux(Account.class)
                .doOnSubscribe(subscription -> System.err.println("doOnSubscribe"))
                .doOnError(throwable -> System.err.println("doOnError"))
                .doOnNext(account -> log.info("next"))
                .log();


        flux.subscribe(System.out::println);

        Thread.sleep(30000);

    }

}
