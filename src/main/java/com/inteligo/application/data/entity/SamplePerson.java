package com.inteligo.application.data.entity;

import javax.persistence.Entity;

import com.inteligo.application.data.AbstractEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Data
public class SamplePerson extends AbstractEntity {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private LocalDate dateOfBirth;
    private String occupation;
    private boolean important;

}
