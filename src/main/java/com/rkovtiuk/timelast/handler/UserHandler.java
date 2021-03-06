package com.rkovtiuk.timelast.handler;

import com.rkovtiuk.timelast.entity.User;
import com.rkovtiuk.timelast.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class UserHandler {

    private final UserRepository repository;

    @Autowired
    public UserHandler(UserRepository repository) {
        this.repository = repository;
    }

    public Mono<ServerResponse> one(ServerRequest request) {
        Mono<User> user = request.bodyToMono(User.class);
        return ServerResponse.ok().build(this.repository.save(user));
    }

    public Mono<ServerResponse> create(ServerRequest request) {
        User user = new User();
        user.setNickname("qa_test_accaount");
        user.setFullName("QA Test");
        return ServerResponse.ok().build(this.repository.save(user));
    }
}
