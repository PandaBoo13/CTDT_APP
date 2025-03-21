package com.example.CTDT_APP.Controller;

import com.example.CTDT_APP.DTO.Request.TaiKhoanCreationRequest;
import com.example.CTDT_APP.DTO.Request.TaiKhoanUpdateRequest;
import com.example.CTDT_APP.DTO.Respone.ApiRespone;
import com.example.CTDT_APP.Entity.TaiKhoan;
import com.example.CTDT_APP.Service.TaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("/taikhoans")
public class TaiKhoanController {
    @Autowired
    private TaiKhoanService taiKhoanService;

    @PostMapping
    public ApiRespone<TaiKhoan> createTaiKhoan(@RequestBody TaiKhoanCreationRequest request) {
        ApiRespone apiRespone= new ApiRespone();
        apiRespone.setResult(taiKhoanService.createTaikhoan(request));
        return apiRespone;
    }

    @GetMapping
    List<TaiKhoan> getTaikhoan(){
        return taiKhoanService.getTaiKhoan();
    }

    @GetMapping("/{mataikhoan}")
    TaiKhoan getTaiKhoanById(@PathVariable("mataikhoan") String mataikhoan){
        return taiKhoanService.getTaiKhoanById(mataikhoan);
    }

    @PutMapping("/{maTaiKhoan}")
    TaiKhoan putTaiKhoanById(@PathVariable("maTaiKhoan") String maTaiKhoan, @RequestBody TaiKhoanUpdateRequest request){
        return taiKhoanService.updateTaiKhoan(maTaiKhoan,request);
    }

    @DeleteMapping("/{maTaiKhoan}")
    void deleteTaiKhoan (@PathVariable("maTaiKhoan")String maTaiKhoan ){
        taiKhoanService.deleteTaiKhoan(maTaiKhoan);
    }

}
