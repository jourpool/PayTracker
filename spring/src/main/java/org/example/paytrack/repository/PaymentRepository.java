package org.example.paytrack.repository;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import org.example.paytrack.model.Payment;
import org.example.paytrack.model.PaymentType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Payment findById(long id);
    Page<Payment> findByDeletedTimeIsNull(Pageable pageable);

    // Filter and Pagination
    Page<Payment> findByCustomerIdAndPaymentTypeId(long customerId, long paymentTypeId, Pageable pageable);
    Page<Payment> findByCustomerIdAndPaymentTypeIdAndAmount(long customerId, long paymentTypeId, BigDecimal amount, Pageable pageable);
}
