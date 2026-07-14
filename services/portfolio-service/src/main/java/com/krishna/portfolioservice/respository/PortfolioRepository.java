package com.krishna.portfolioservice.respository;

import com.krishna.portfolioservice.model.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PortfolioRepository
        extends JpaRepository<Portfolio, Long> {

    Optional<Portfolio> findByClientIdAndSymbol(
            String clientId,
            String symbol);
}
