package com.buyzilla.services.authservice.vo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;



@Data
public class AdminVo {
    Integer id;
    @NotNull
    String email,password;
}
