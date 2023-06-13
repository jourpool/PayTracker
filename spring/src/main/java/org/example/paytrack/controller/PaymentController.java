package org.example.paytrack.controller;

import org.example.paytrack.model.Payment;
import org.example.paytrack.model.PaymentType;
import org.example.paytrack.repository.PaymentRepository;
import org.example.paytrack.repository.PaymentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

//@CrossOrigin(origins = "http://localhost:8080")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class PaymentController {

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    PaymentTypeRepository paymentTypeRepository;

    @GetMapping("/payment")
    public ResponseEntity<Map<String, Object>> getAllPayments(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") @Max(100) int size
    ) {
        try {
            List<Payment> paymentList;
            Pageable paging = PageRequest.of(page, size);

            Page<Payment> pagePayment;

            pagePayment = paymentRepository.findByDeletedTimeIsNull(paging);

            paymentList = pagePayment.getContent();

            if(paymentList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            Map<String, Object> response = new HashMap<>();
            response.put("payments", paymentList);
            response.put("currentPage", pagePayment.getNumber());
            response.put("totalItems", pagePayment.getTotalElements());
            response.put("totalPages", pagePayment.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/payment/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable("id") long id) {
        try {
            Payment payment = paymentRepository.findById(id);

            // Id not found
            if (payment == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(payment, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/payment")
    public ResponseEntity<Payment> createPayment(@RequestBody Payment payment) {
        try {
            Timestamp currentDate = new Timestamp(System.currentTimeMillis());
            Payment _payment = paymentRepository
                    .save(new Payment(payment.getAmount(), payment.getPaymentTypeId(), currentDate, payment.getCustomerId(), null));
            return new ResponseEntity<>(_payment, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/payment/{id}")
    public ResponseEntity<Payment> updatePayment(@PathVariable("id") long id, @RequestBody Payment payment) {
        try {
            Payment _payment = paymentRepository.findById(id);

            // Id not found
            if (payment == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            _payment.setAmount(payment.getAmount());
            _payment.setCustomerId(payment.getCustomerId());
            _payment.setPaymentType(payment.getPaymentTypeId());

            return new ResponseEntity<>(paymentRepository.save(_payment), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/payment/{id}")
    public ResponseEntity<Payment> deletePayment(@PathVariable("id") long id, @RequestBody Payment payment) {
        try {
            Payment _payment = paymentRepository.findById(id);

            // Id not found
            if (_payment == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            _payment.setDeletedTime(new Timestamp(System.currentTimeMillis()));
            return new ResponseEntity<>(paymentRepository.save(_payment), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/payment/filter")
    public ResponseEntity<Map<String, Object>> getPaymentsByFilter(
            @RequestParam long customerId,
            @RequestParam String typeName,
            @RequestParam(required = false) BigDecimal amount,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") @Max(100) int size
    ) {
        try {
            List<Payment> paymentList;
            Pageable paging = PageRequest.of(page, size);

            Page<Payment> pagePayment;
            PaymentType paymentType = paymentTypeRepository.findByName(typeName);

            // Payment Type not found
            if (paymentType == null) {
                Map<String, Object> response = new HashMap<>();
                response.put("message", "Invalid payment type name");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

            if (amount != null) {
                // Filter by amount
                pagePayment = paymentRepository.findByCustomerIdAndPaymentTypeIdAndAmount(customerId, paymentType.getId(), amount, paging);
            } else {
                // No filter
                pagePayment = paymentRepository.findByCustomerIdAndPaymentTypeId(customerId, paymentType.getId(), paging);
            }

            paymentList = pagePayment.getContent();

            if(paymentList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            Map<String, Object> response = new HashMap<>();
            response.put("payments", paymentList);
            response.put("currentPage", pagePayment.getNumber());
            response.put("totalItems", pagePayment.getTotalElements());
            response.put("totalPages", pagePayment.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
