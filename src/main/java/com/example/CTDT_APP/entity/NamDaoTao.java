package com.example.CTDT_APP.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class NamDaoTao {
    @Id
    @Column(name = "Nam")
    private Integer id;

//    @JsonIgnore
//    @ManyToMany(mappedBy = "namDaoTaos")
//    private List<ChuongTrinhDaoTao> chuongTrinhDaoTaos;
}