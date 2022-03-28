package com.mycompany.user;

import javax.persistence.*;

@Entity     // DB의 테이블과 Class(VO, DTO)와 맵핑한다면 반드시 @Entity를 붙여주어야 함
@Table(name = "users")      // @Table : 맵핑할 테이블을 지정.    name속성 : name : 매핑할 테이블의 이름을 지정
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // id 컬럽값이 DB에 의해 자동생성
    private Integer id;

    // @Column : 객체 필드와 DB 테이블 컬럼을 맵핑.      nullable : NULL을 허용할지, 허용하지 않을지 결정
    @Column(nullable = false, unique = true, length = 45)     // 이메일 값이 필수임. user테이블에서 이메일 값이 고유함.  이메일길이 최대45.
    private String email;

    @Column(length = 15, nullable = false)        // 비밀번호 길이 최대 15.
    private String password;

    @Column(length = 45, nullable = false, name = "first_name")
    private String firstName;

    @Column(length = 45, nullable = false, name = "last_name")
    private String lastName;

    private boolean enabled;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
