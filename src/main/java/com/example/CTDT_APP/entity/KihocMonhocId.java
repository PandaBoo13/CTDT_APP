package com.example.CTDT_APP.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class KihocMonhocId implements Serializable {
    @Serial
    private static final long serialVersionUID = -3805420530762826773L;
    @Size(max = 21)
    @NotNull
    @Column(name = "MaKi", nullable = false, length = 21)
    private String maKi;

    @Size(max = 21)
    @NotNull
    @Column(name = "MaMon", nullable = false, length = 21)
    private String maMon;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        KihocMonhocId entity = (KihocMonhocId) o;
        return Objects.equals(this.maKi, entity.maKi) &&
                Objects.equals(this.maMon, entity.maMon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maKi, maMon);
    }

}