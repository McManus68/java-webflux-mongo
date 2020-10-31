package com.mcmanus.webfluxmongo.persistence;

import com.mcmanus.webfluxmongo.model.Account;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface AccountRepository extends ReactiveMongoRepository<Account, String> {
    Flux<Account> findAllByValue(String value);
    Flux<Account> findAllByOwner(String owner);
    Mono<Account> findFirstByOwner(Mono<String> owner);
}