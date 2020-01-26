package com.wolf.hookahshopee.legacy.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDTO_POST {

    @NotNull
    @Min(1)
    private Long quantity;

    @NotNull
    private UUID productUUID;
}
