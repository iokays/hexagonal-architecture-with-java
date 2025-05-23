package com.iokays.authorization.core.domain.user;

import com.iokays.common.domain.jpa.IdentifiedValueObject;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "t_authorities")
public class Authority extends IdentifiedValueObject<Authority> {

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User user;

    private String authority;

    public String authority() {
        return this.authority;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        final Authority authority1 = (Authority) o;
        return Objects.equals(user, authority1.user) && Objects.equals(authority, authority1.authority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, authority);
    }
}
