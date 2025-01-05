package com.blog.payload;



import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO {

	private int id;
	
	@NotEmpty
	@Size(min = 2, message = "username must be min of 2 characters !!")
	private String name;
	@Email(message = "email is not valid")
	private String email;
	@NotEmpty
	@Size(min = 4,max = 8, message = "password must be min of 2 characters and max 8 !!")
	private String password;
	@NotEmpty
	private String about;
	
}
