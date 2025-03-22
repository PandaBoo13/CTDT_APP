package com.example.CTDT_APP.service;

import com.example.CTDT_APP.entity.ChuongTrinhDaoTao;
import com.example.CTDT_APP.repository.CTDTRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CTDTService {
    private final CTDTRepository ctdtRepo;

    ChuongTrinhDaoTao createCTDT() {
        return null;
    }
}
