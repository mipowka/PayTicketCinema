package org.example.payticketcinema.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
public class UserAuthDto {

   // @NotBlank
            // @Length(min = 6, max = 20,message = "Длина username от 6 до 20")
    private  String username;

  //  @NotBlank
  //  @Pattern(regexp = "\\d{5}!\\d{5}" ,message = "Пароль 5 чисел ! 5 чисел (пример 12345!67890)")
    private  String password;
}
