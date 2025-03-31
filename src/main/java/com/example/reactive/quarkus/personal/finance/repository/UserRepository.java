package com.example.reactive.quarkus.personal.finance.repository;

import com.example.reactive.quarkus.personal.finance.model.entity.User;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {
    public Uni<User> getUserById(long id) {
        return findById(id);
    }
}
