package com.example.CTDT_APP.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class NamCtdtId implements Serializable {
    private static final long serialVersionUID = -7453805339490457690L;
    @NotNull
    @Column(name = "Nam")
    private Integer nam;

    @Size(max = 21)
    @NotNull
    @Column(name = "MaCTDT")
    private String maCTDT;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        NamCtdtId entity = (NamCtdtId) o;
        return Objects.equals(this.maCTDT, entity.maCTDT) &&
                Objects.equals(this.nam, entity.nam);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maCTDT, nam);
    }

}