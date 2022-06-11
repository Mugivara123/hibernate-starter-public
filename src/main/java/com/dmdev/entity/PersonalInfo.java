package com.dmdev.entity;

import java.io.Serial;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class PersonalInfo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private String firstName;
    private String lastName;

    //@Convert(converter = BirthdayConverter.class)
    private Birthday birthDate;
}
