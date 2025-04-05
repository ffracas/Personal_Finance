package com.example.reactive.quarkus.personal.finance.repository;

import com.example.reactive.quarkus.personal.finance.model.entity.Investment;
import io.quarkus.hibernate.reactive.panache.PanacheRepositoryBase;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.quarkus.logging.Log;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.UUID;

/**
 * {@code InvestmentRepository} is a repository class responsible for performing database
 * operations on {@link Investment} entities. It leverages Panache Repository capabilities
 * for simplified reactive persistence.
 * <p>
 * This class is annotated with {@link ApplicationScoped} to ensure that it is managed by the CDI container.
 * Each method is annotated with {@code @WithTransaction} to guarantee that the database operations are executed within a transactional context.
 * <p>
 * The repository provides methods to save, retrieve, list, and delete investments using reactive types {@link Uni} and {@link Multi}.
 *
 * @see PanacheRepositoryBase
 * @see Investment
 */
@ApplicationScoped
public class InvestmentRepository implements PanacheRepositoryBase<Investment, UUID> {

    /**
     * Persists the provided {@link Investment} entity in the database.
     * <p>
     * This method is executed within a transactional context and returns a {@link Uni}
     * that emits the persisted investment entity. In case of any persistence failures,
     * the failure is logged using the configured logging mechanism.
     *
     * @param investment the investment entity to be persisted
     * @return a {@link Uni} emitting the persisted {@link Investment} entity,
     * or propagating an error if the operation fails
     */
    @WithTransaction
    public Uni<Investment> saveInvestment(Investment investment) {
        return persist(investment)
                .onFailure()
                .invoke(Log::error);
    }

    /**
     * Retrieves all {@link Investment} entities from the database.
     * <p>
     * This method leverages the reactive {@link Multi} type to emit a stream of results.
     * It fetches all available investments and converts the resulting list into a multi-item stream.
     *
     * @return a {@link Multi} emitting a {@link List} of all {@link Investment} entities
     */
    @WithTransaction
    public Multi<List<Investment>> getAllInvestment() {
        return listAll().toMulti();
    }

    /**
     * Deletes an {@link Investment} entity identified by the specified UUID.
     * <p>
     * The deletion is executed within a transactional context. The method returns a {@link Uni}
     * that emits {@code true} if the deletion was successful, or {@code false} if no entity was found for deletion.
     *
     * @param investmentId the unique identifier (UUID) of the investment to delete
     * @return a {@link Uni} emitting a {@code Boolean} indicating whether the deletion was successful
     */
    @WithTransaction
    public Uni<Boolean> deleteInvestment(UUID investmentId) {
        return deleteById(investmentId);
    }

    /**
     * Retrieves an {@link Investment} entity by its unique identifier.
     * <p>
     * This method performs a reactive lookup using the provided UUID and returns a {@link Uni}
     * that emits the corresponding investment if found. If no matching investment is found,
     * the {@link Uni} may emit a {@code null} or an empty result, depending on the underlying implementation.
     *
     * @param investmentId the unique identifier (UUID) of the investment to retrieve
     * @return a {@link Uni} emitting the {@link Investment} entity associated with the given UUID,
     * or an empty result if not found
     */
    @WithTransaction
    public Uni<Investment> getInvestmentById(UUID investmentId) {
        return findById(investmentId);
    }
}
