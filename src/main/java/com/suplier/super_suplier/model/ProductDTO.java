package com.suplier.super_suplier.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.util.UUID;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ProductDTO {

    private UUID id;

    @NotNull
    private UUID supplierId;

    @NotNull
    @Size(max = 255)
    private String productName;

    @NotNull
    @Digits(integer = 10, fraction = 2)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Schema(type = "string", example = "68.08")
    private BigDecimal productPrice;

    @NotNull
    private UUID products;

}
