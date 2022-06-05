package com.mus.iam.entities.embedded;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Embeddable;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Embeddable
public class CompanyAddress implements Serializable {
	
	private static final long serialVersionUID = 4145928058196660600L;
	private String address;
	private String numberAddress;
	private String complement;
	private String neighbourhood;
	private String zipCode;
	private String city;
	private String state;

}
