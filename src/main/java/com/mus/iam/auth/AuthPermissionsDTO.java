package com.mus.iam.auth;

import com.mus.iam.entities.Permission;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthPermissionsDTO {

	private String name;
	
	public AuthPermissionsDTO(Permission permission) {
		this.name = permission.getName();
	}
	
}
