package io.github.neharoshni.demo.repositories;


import io.github.neharoshni.demo.entities.PortfolioInstrument;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PortfolioRepository extends JpaRepository<PortfolioInstrument, Long> {
    // custom query methods
    List<PortfolioInstrument> findByUser_Id(Long userId);
}
