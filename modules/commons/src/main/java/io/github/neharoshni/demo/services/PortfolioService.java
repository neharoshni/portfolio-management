package io.github.neharoshni.demo.services;

import io.github.neharoshni.demo.entities.PortfolioInstrument;
import io.github.neharoshni.demo.repositories.PortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PortfolioService {
    @Autowired
    private PortfolioRepository portfolioRepository;

    public List<PortfolioInstrument> getAllPortfolioInstrumentsByUserId(Long userId) {
        return portfolioRepository.findByUser_Id(userId);
    }
}
