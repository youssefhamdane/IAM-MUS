package com.mus.iam.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Entity(name = "PermissionEntity")
@Table(name = "tbl_permission")
public class Permission implements Serializable {
	
	@Transient
	private static final long serialVersionUID = 627952684503591931L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tPermissionSeq")
	@SequenceGenerator(name = "tPermissionSeq", sequenceName = "tbl_permission_seq", allocationSize = 1)
	private Long permissionId;
	
	@Column(name = "name")
	private String name;
	
	@ManyToMany(mappedBy = "permissions", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Role> roles = new ArrayList<>();
	
	@Override
    public int hashCode() {
        if (permissionId != null) {
            return permissionId.hashCode();
        } else if (name != null) {
            return name.hashCode();
        }
        return 0;
    }

    @Override
    public boolean equals(Object another) {
        if (another == null || !(another instanceof Permission))
            return false;
        Permission anotherPermission = (Permission) another;
        return (anotherPermission.permissionId != null && (anotherPermission.permissionId == this.permissionId))
                || (anotherPermission.permissionId == null && anotherPermission.name != null && (anotherPermission.name.equals(this.name)));
    }

	public Permission(String name) {
		this.name = name;
	}

}
