package com.mcmanus.webfluxmongo.controller;

import com.mcmanus.webfluxmongo.model.Account;
import com.mcmanus.webfluxmongo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    private AccountService service;

    @PostMapping
    Mono<Account> save(@RequestBody Account account) {
        return service.save(account);
    }

    @GetMapping("/test")
    Mono<Account> test() {
        Account a = new Account();
        a.setId("7878788");
        a.setValue(78d);
        a.setOwner("POPO");
        return Mono.just(a);
    }

    @GetMapping("/{value}")
    Flux<Account> getAllByValue(@PathVariable String value) {
        return service.getAllByValue(value);
    }

    @GetMapping(value = "/owner/{owner}", produces = "application/stream+json")
    Flux<Account> getAllByOwner(@PathVariable String owner) {
        return service.getAllByOwner(owner);
    }

    @GetMapping("/owner/{owner}/first")
    Mono<Account> getFirstByOwner(@PathVariable String owner) {
        return service.getFirstByOwner(Mono.just(owner));
    }

    @GetMapping("/initialize")
    Flux<Account> initialize() {
        return service.initialize();
    }

}
