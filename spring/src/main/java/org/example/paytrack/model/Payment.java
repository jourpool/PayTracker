package org.example.paytrack.model;
import java.math.BigDecimal;
import java.sql.Timestamp;
import jakarta.persistence.*;

@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "payment_id")
    private long id;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "payment_type_id")
    private long paymentTypeId;

    @Column(name = "date")
    private Timestamp date;

    @Column(name = "customer_id")
    private long customerId;

    @Column(name = "deleted_time")
    private Timestamp deletedTime;

    public Payment() {

    }

    public Payment(BigDecimal amount, long paymentTypeId, Timestamp date, long customerId, Timestamp deletedTime) {
        this.date = date;
        this.amount = amount;
        this.customerId = customerId;
        this.deletedTime = deletedTime;
        this.paymentTypeId = paymentTypeId;
    }

    public long getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public long getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentType(long paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public Timestamp getDeletedTime() {
        return deletedTime;
    }

    public void setDeletedTime(Timestamp deletedTime) {
        this.deletedTime = deletedTime;
    }
}
