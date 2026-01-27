package com.pavan.billservice.controller;

import com.pavan.billservice.entity.Bill;
import com.pavan.billservice.service.BillService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/bills")
@RequiredArgsConstructor
public class BillController {

    private final BillService billService;

    @PostMapping
    public Bill createBill(@RequestBody Bill bill,
                           Authentication authentication) {

        UUID userId = (UUID) authentication.getPrincipal();
        return billService.createBill(bill, userId);
    }

    @GetMapping("/my")
    public List<Bill> getMyBills(Authentication authentication) {

        UUID userId = (UUID) authentication.getPrincipal();
        return billService.getBills(userId);
    }

    @PutMapping("/{id}/pay")
    public void payBill(@PathVariable Long id,
                        Authentication authentication) {

        UUID userId = (UUID) authentication.getPrincipal();
        billService.markAsPaid(id, userId);
    }
}


