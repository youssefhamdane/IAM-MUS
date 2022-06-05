package com.mus.iam.entities;

import com.mus.iam.entities.embedded.Contact;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Entity(name = "UserEntity")
@Table(
		name = "tbl_user", 
		indexes =  @Index(
		        name = "idx_tbl_user_email",
		        columnList = "email",
		        unique = true
		    )
		)
public class User implements Serializable {
	
	@Transient
	private static final long serialVersionUID = 111935761710887746L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tUserSeq")
	@SequenceGenerator(name = "tUserSeq", sequenceName = "tbl_user_seq", allocationSize = 1)
	private Long userId;

	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "uuid", updatable = false, nullable = false)
	private UUID uuid;
	
	@NotNull @NotEmpty @Length(min = 3)
	@Column(name = "login")
	private String login;
	
	@NotNull @NotEmpty
	@Column(name = "password_user")
	private String password;
	
	@NotNull @NotEmpty
	@Column(name = "name")
	private String name;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "tbl_user_roles",
	joinColumns = @JoinColumn(
			name = "user_id", referencedColumnName = "userId", foreignKey = @ForeignKey(name = "fk_tbl_user_roles_user")), 
	inverseJoinColumns = @JoinColumn(
		name = "role_id", referencedColumnName = "roleId", foreignKey = @ForeignKey(name = "fk_tbl_user_roles_role")))
	private List<Role> roles = new ArrayList<>();
	
	@Column(name = "date_inclusion")
	private LocalDate dateInclusion;
	
	@Column(name = "register_status")
    private Boolean registerStatus;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="company_id", foreignKey = @ForeignKey(name = "fk_tbl_user_company"))
	private Company company;
	
	@Embedded
	private Contact contact;

	//Constructor's
	public User(UUID uuid, String login, String password, String name, List<Role> roles, LocalDate dateInclusion) {
		super();
		this.uuid = uuid;
		this.login = login;
		this.password = password;
		this.name = name;
		this.roles = roles;
		this.dateInclusion = dateInclusion;
	}
	
}
