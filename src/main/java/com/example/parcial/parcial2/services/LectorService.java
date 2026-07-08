package com.example.parcial.parcial2.services;

import com.example.parcial.parcial2.domain.dtos.LectorRequestDto;
import com.example.parcial.parcial2.domain.entities.Lector;
import com.example.parcial.parcial2.repositories.LectorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LectorService {

    private final LectorRepository lectorRepository;

    public LectorService(LectorRepository lectorRepository) {
        this.lectorRepository = lectorRepository;
    }

    public Lector registerLector(LectorRequestDto dto) {
        return null;
    }

    public Lector getLectorById(UUID id) {
        return null;
    }

    public List<Lector> getAllLectors() {
        return null;
    }

    public Lector updateLector(UUID id, LectorRequestDto dto) {
        return null;
    }

    public void deleteLector(UUID id) {
    }
}
