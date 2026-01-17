package com.burcin.wallet.enums;

public enum TransactionStatus {
    PENDING,    // Transaction started but not yet completed
    SUCCESS,    // Transaction completed successfully
    FAILED,     // Transaction failed due to an error
    CANCELLED   // Transaction cancelled by user or system
}
