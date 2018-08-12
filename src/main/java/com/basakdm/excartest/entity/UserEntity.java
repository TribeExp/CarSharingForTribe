package com.basakdm.excartest.entity;

import com.basakdm.excartest.enum_ent.Roles;
import com.basakdm.excartest.enum_ent.TypeOfNotify;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String lastName;
    @Column
    private String firstName;
    @Column
    private String middleName;
    @Column
    private String password;
    @Column
    private String mail;
    @Column
    private String seriesPassport;
    @Column
    private Long numberSeria;
    @Column
    private String whoGetPasport;
    @Column
    private Date whenGetPassport;
    @Column
    private Date birthDate;
    @Column
    private String birthPlace;
    @Column
    private String serialDriveDoc;
    @Column
    private Long numDriveDoc;
    @Column
    private String whoGetDriveDoc;
    @Column
    private Date whenGetDriveDoc;
    @Column
    private Date expirtyDate;
    @Column
    private String categoryDriveDoc;
    @Column
    private String photo;
    @Column
    private Long phoneNum;
    @Column
    private Boolean notify;
    @Column
    private Long idCar;
    @Column
    private Long price;
    @Column
    private Long priceAdd;
    @Column
    private Long finPrice;
    @Column
    private Date timeForDrive;
    @Column
    private String causeAddPrice;
    @ElementCollection(targetClass = Roles.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Roles> role;
    @Column
    private Boolean active; //add!!!!!!!!!!!!!!!!!!!!!!!!
    @Column
    @Enumerated(EnumType.STRING)
    private TypeOfNotify typeOfNotify;

    public String getPassword() {
        return password;
    }


    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Set<Roles> getRole() {
        return role;
    }

    public void setRole(Set<Roles> role) {
        this.role = role;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

