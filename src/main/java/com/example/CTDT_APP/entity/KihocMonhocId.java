package com.example.CTDT_APP.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class KihocMonhocId implements Serializable {
    @Serial
    private static final long serialVersionUID = 7133533290197256102L;
}