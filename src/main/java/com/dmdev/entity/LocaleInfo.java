package com.dmdev.entity;

import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@Data
@Embeddable
public class LocaleInfo {

    private String lang;
    private String description;

}
