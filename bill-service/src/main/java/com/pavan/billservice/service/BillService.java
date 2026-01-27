package com.pavan.billservice.service;

import com.pavan.billservice.entity.Bill;
import com.pavan.billservice.repository.BillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BillService {

    private final BillRepository billRepository;

    public Bill createBill(Bill bill, UUID userId) {
        bill.setUserId(userId);
        return billRepository.save(bill);
    }

    public List<Bill> getBills(UUID userId) {
        return billRepository.findByUserId(userId);
    }

    public void markAsPaid(Long billId, UUID userId) {
        Bill bill = billRepository.findByIdAndUserId(billId, userId)
                .orElseThrow(() -> new RuntimeException("Bill not found"));

        bill.setPaid(true);
        billRepository.save(bill);
    }
}

