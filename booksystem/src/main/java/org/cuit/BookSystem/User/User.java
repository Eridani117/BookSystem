package org.cuit.BookSystem.User;

import org.springframework.stereotype.Repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Repository
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	private String id;
	private String username;
	private String password;
	private String email;
	private String cellphone;
	private String address;

}
