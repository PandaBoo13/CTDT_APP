package com.example.CTDT_APP.Service;

import com.example.CTDT_APP.Repository.NhanVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NhanVienService {
    @Autowired
    private NhanVienRepository nhanVienRepository;

}
