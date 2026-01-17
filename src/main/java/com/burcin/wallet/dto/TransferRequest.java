package com.burcin.wallet.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;
import jakarta.validation.constraints.DecimalMin;
@Data
public class TransferRequest {

    @NotNull(message = "Sender user ID is required")
    private Long senderUserId;

    @NotNull(message = "Receiver user ID is required")
    private Long receiverUserId;

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.01", message = "Amount must be greater than zero")
    private BigDecimal amount;
    
}
