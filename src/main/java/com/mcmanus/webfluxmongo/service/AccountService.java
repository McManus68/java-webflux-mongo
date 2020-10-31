package com.mcmanus.webfluxmongo.service;

import com.mcmanus.webfluxmongo.model.Account;
import com.mcmanus.webfluxmongo.persistence.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class AccountService {

    @Autowired
    private AccountRepository repository;

    public Flux<Account> getAllByValue(String value) {
        return repository.findAllByValue(value);
    }

    public Flux<Account> getAllByOwner(String owner) {
        return repository.findAllByOwner(owner).delayElements(Duration.ofMillis(100));
    }

    public Mono<Account> getFirstByOwner(Mono<String> owner) {
        return repository.findFirstByOwner(owner);
    }

    public Mono<Account> save(Account account) {
        return repository.save(account);
    }

    public Flux<Account> initialize() {

        String[] owners = new String[] {"Toto", "Manu", "Marcel", "Gepeto", "Gaston"};

        return  Flux.range(0, 10000)
                .map(integer -> {
                    Account a = new Account();
                    a.setOwner(owners[(int) (Math.random() * owners.length)]);
                    a.setValue(Math.random() * 5000);
                    return a;
                })
                .doOnNext(account -> repository.save(account));
    }

}
