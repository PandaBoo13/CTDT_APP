package com.example.CTDT_APP.service;

import com.example.CTDT_APP.entity.NamDaoTao;
import com.example.CTDT_APP.repository.NamDaoTaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class NamDaoTaoService {

    private final NamDaoTaoRepository namDaoTaoRepo;

    public List<Integer> getAllNamDaoTao() {
        return namDaoTaoRepo.findAll()
                .stream()
                .map(NamDaoTao::getNam)
                .toList();
    }
}
