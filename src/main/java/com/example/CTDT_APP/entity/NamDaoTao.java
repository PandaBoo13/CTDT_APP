package com.example.CTDT_APP.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NamDaoTao {
    @Id
    @Column(name = "Nam")
    private Integer nam;

    @JsonIgnore
    @ManyToMany(mappedBy = "namDaoTaos")
    private List<ChuongTrinhDaoTao> chuongTrinhDaoTaos;
}