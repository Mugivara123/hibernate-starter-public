package com.dmdev.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKey;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.OrderColumn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.SortComparator;
import org.hibernate.annotations.SortNatural;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "name")
@ToString(exclude = "users")
@Builder
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

    @Builder.Default
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    @MapKey(name = "userName")
    @SortNatural
    private Map<String,User> users = new TreeMap<>();

    @Builder.Default
    @ElementCollection
    @CollectionTable(name="company_locale", joinColumns = @JoinColumn(name = "company_id"))
//    @AttributeOverride(name = "lang",column = @Column(name = "language"))
//    private  List<LocaleInfo> locales = new ArrayList<>();
//    @Column(name = "description")
    @MapKeyColumn(name = "lang")
    private  Map<String,String> locales = new HashMap<>();

    public void addUser(User user){
        users.put(user.getUserName(), user);
        user.setCompany(this);
    }
}
