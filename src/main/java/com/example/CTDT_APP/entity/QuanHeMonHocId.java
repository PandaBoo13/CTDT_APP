package com.example.CTDT_APP.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class QuanHeMonHocId implements Serializable {
    @Serial
    private static final long serialVersionUID = -2553072077000056443L;

    @Column(name = "MaMonChinh")
    private String maMonChinh;

    @Column(name = "MaMonLienQuan")
    private String maMonLienQuan;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        QuanHeMonHocId entity = (QuanHeMonHocId) o;
        return Objects.equals(this.maMonChinh, entity.maMonChinh) &&
                Objects.equals(this.maMonLienQuan, entity.maMonLienQuan);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maMonChinh, maMonLienQuan);
    }
}