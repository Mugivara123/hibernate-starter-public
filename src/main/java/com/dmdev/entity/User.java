package com.dmdev.entity;


import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "userName")
@ToString(exclude = {"company", "profile", "userChats"})
@Entity
@Builder
@Table(name = "users", schema = "public")
@TypeDef(name = "dmdev", typeClass = JsonBinaryType.class)
public class User implements Comparable<User>,BaseEntity<Long>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @AttributeOverride(name = "birthDate", column = @Column(name = "birth_date"))
    private PersonalInfo personalInfo;

    @Column(unique = true)
    private String userName;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Type(type = "dmdev")
    private String info;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id") // company_id - дефолт
    private Company company;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private Profile profile;

    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<UserChat> userChats = new ArrayList<>();

    @Override
    public int compareTo(User o) {
        return userName.compareTo(o.userName);
    }
}



