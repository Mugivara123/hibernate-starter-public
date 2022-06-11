package com.dmdev.entity;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;


public interface  BaseEntity<T extends Serializable> {

    void setId(T id);
    T getId();
}
