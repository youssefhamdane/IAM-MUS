package com.mus.iam.entities;

import com.mus.iam.entities.embedded.CompanyAddress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Entity(name = "CompanyEntity")
@Table(name = "tbl_company")
public class Company implements Serializable {

	@Transient
	private static final long serialVersionUID = 3941297684663128700L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tCompanySeq")
    @SequenceGenerator(name = "tCompanySeq", sequenceName = "tbl_company_seq", allocationSize = 1)
    private Long companyId;
	
    @Column(name = "corporate_name")
    private String corporateName;
    
    @Column(name = "cnpj")
    private String cnpj;
  
    @Column(name = "market_segment")
    private String marketSegment;
    
    @Embedded
    private CompanyAddress companyAddress;
    
    @Column(name = "date_create")
    private LocalDate dateCreate;
    
    @Column(name = "register_status")
    private Boolean registerStatus;
    
    @OneToMany(mappedBy = "company", 
    		targetEntity = User.class, 
    		fetch = FetchType.LAZY, 
    		cascade = CascadeType.ALL)
	private List<User> users;

}
