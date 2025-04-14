package com.example.CTDT_APP.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
    @OneToMany(mappedBy = "namDaoTao", fetch = jakarta.persistence.FetchType.LAZY)
    private List<NamCTDT> namCtDtList;
}
