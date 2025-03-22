package com.example.CTDT_APP.service;

import com.example.CTDT_APP.dto.request.KhoaCreationRequest;
import com.example.CTDT_APP.dto.request.KhoaUpdateRequest;
import com.example.CTDT_APP.entity.Khoa;
import com.example.CTDT_APP.exception.AppException;
import com.example.CTDT_APP.repository.KhoaRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class KhoaService {
    private final KhoaRepository khoaRepo;
    private final ModelMapper mapper;

    public List<Khoa> getAllKhoa() {
        return khoaRepo.findAll();
    }

    public Khoa createKhoa(KhoaCreationRequest req) {
        if (khoaRepo.existsById(req.getMaKhoa())) throw new AppException("Khoa da ton tai");
        return khoaRepo.save(mapper.map(req, Khoa.class));
    }

    public Khoa updateKhoa(String maKhoa, KhoaUpdateRequest req) {
        Khoa khoa = khoaRepo.findById(maKhoa)
                .orElseThrow(() -> new AppException("Khoa khong ton tai"));

        mapper.map(req, khoa);

        return khoaRepo.save(khoa);
    }

    public void deleteKhoa(String maKhoa) {
        if (!khoaRepo.existsById(maKhoa)) throw new AppException("Khoa khong ton tai");
        khoaRepo.deleteById(maKhoa);
    }
}
