package com.mus.iam.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "RoleEntity")
@Table(name = "tbl_role")
public class Role implements GrantedAuthority{
	
	@Transient
	private static final long serialVersionUID = -3668685073592312997L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tRoleSeq")
	@SequenceGenerator(name = "tRoleSeq", sequenceName = "tbl_role_seq", allocationSize = 1)
	private Long roleId;
	
	@Column(name = "name")
	private String name;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "tbl_role_permissions",
	joinColumns = @JoinColumn(
			name = "role_id", referencedColumnName = "roleId", foreignKey = @ForeignKey(name = "fk_tbl_role_permissions_role")), 
	inverseJoinColumns = @JoinColumn(
		name = "permission_id", referencedColumnName = "permissionId", foreignKey = @ForeignKey(name = "fk_tbl_role_permissions_permission")))
	private List<Permission> permissions = new ArrayList<>();
	
	@ManyToMany(mappedBy = "roles")
	private List<User> users = new ArrayList<>();

	@Override
	public String getAuthority() {
		return this.permissions.stream()
	        	.map(Permission::getName)
	        	.collect(Collectors.joining(","));
	}

	@Override
    public int hashCode() {
        if (roleId != null) {
            return roleId.hashCode();
        } else if (name != null) {
            return name.hashCode();
        }
        return 0;
    }

    @Override
    public boolean equals(Object another) {
        if (another == null || !(another instanceof Role))
            return false;
        Role anotherRole = (Role) another;
        return anotherRole.roleId != null && (anotherRole.roleId == this.roleId);
    }

    @Override
    public String toString() {
        return name;
    }

	public Role(String name) {
		this.name = name;
	}
    
}
