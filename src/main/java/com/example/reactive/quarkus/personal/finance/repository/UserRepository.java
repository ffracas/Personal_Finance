package com.example.reactive.quarkus.personal.finance.repository;

import com.example.reactive.quarkus.personal.finance.model.entity.User;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {
    @WithTransaction
    public Uni<User> getUserById(long id) {
        return findById(id);
    }

    @WithTransaction
    public Uni<List<User>> getAllUser() {
        return listAll();
    }

    @WithTransaction
    public Uni<User> saveUser(User user) {
        return persist(user)
                .onFailure()
                .invoke(Log::error);
    }

    @WithTransaction
    public Uni<Void> deleteUser(long userId) {
        return deleteById(userId)
                .onFailure()
                .invoke(Log::error)
                .replaceWithVoid();
    }
}
