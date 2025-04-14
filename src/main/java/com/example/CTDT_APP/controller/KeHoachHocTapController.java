package com.example.CTDT_APP.controller;

import com.example.CTDT_APP.dto.request.KeHoachHocTapCreationRequest;
import com.example.CTDT_APP.dto.response.ApiResponse;
import com.example.CTDT_APP.dto.response.KeHoachHocTapDetailsResponse;
import com.example.CTDT_APP.dto.response.KeHoachHocTapResponse;
import com.example.CTDT_APP.service.KeHoachHocTapService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/ke-hoach-hoc-tap")
@RequiredArgsConstructor
public class KeHoachHocTapController {

    private final KeHoachHocTapService keHoachHocTapService;

    // Create: Tạo mới Kế hoạch học tập (KeHoachHocTap)
    @PostMapping
    public ResponseEntity<ApiResponse> createKeHoachHocTap(
            @Valid @RequestBody KeHoachHocTapCreationRequest req) {
        // Service trả về entity được lưu; nếu cần có thể map sang DTO nhưng ở đây ta trả về trực tiếp
        var createdKeHoach = keHoachHocTapService.createKeHoachHocTap(req);
        ApiResponse response = ApiResponse.builder()
                .code(201)
                .message("Tạo kế hoạch học tập thành công")
                .data(createdKeHoach)
                .build();
        return ResponseEntity.ok(response);
    }

    // Read: Lấy danh sách các KeHoachHocTap theo mã CTDT,
    // chuyển đổi sang DTO KeHoachHocTapResponse (bao gồm trường tenChuyenNganh)
    @GetMapping("/ctdt/{maCTDT}")
    public ResponseEntity<ApiResponse> getAllKeHoachHocTap(@PathVariable String maCTDT) {
        List<KeHoachHocTapResponse> list = keHoachHocTapService.getAllKehoachHocTap(maCTDT);
        ApiResponse response = ApiResponse.builder()
                .code(200)
                .message("Lấy danh sách kế hoạch học tập thành công")
                .data(list)
                .build();
        return ResponseEntity.ok(response);
    }


    // Read: Lấy chi tiết KeHoachHocTap (danh sách KiHoc với danh sách MonHoc) theo mã KHHT
    @GetMapping("/{maKTHHT}/details")
    public ResponseEntity<ApiResponse> getKeHoachHocTapDetails(@PathVariable String maKTHHT) {
        List<KeHoachHocTapDetailsResponse> details = keHoachHocTapService.getAllKeHoachHocTapDetails(maKTHHT);
        ApiResponse response = ApiResponse.builder()
                .code(200)
                .message("Lấy chi tiết kế hoạch học tập thành công")
                .data(details)
                .build();
        return ResponseEntity.ok(response);
    }
}
