package com.pplbo.ecommerce.paymentservice.event;

import com.pplbo.ecommerce.paymentservice.model.UserPayment;
import com.pplbo.ecommerce.paymentservice.model.Transaction;

public class ValidatePayment {
    private Transaction transaction;

    public ValidatePayment(Transaction transaction) {
        this.transaction = transaction;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
