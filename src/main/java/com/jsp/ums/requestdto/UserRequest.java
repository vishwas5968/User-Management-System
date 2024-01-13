package com.jsp.ums.requestdto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

	@NotEmpty(message = "Username cannot be null")
	private String userName;
	
	@NotNull
	@NotBlank(message = "Email cannot be blank")
	private String email;
	
	@NotNull
	@NotBlank
	private String password;
}
