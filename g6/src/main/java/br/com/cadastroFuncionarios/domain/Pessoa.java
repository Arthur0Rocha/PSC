package br.com.cadastroFuncionarios.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="funcionario_id")
    private Integer id;
    @Column(name="funcionario_name", length=50)
    private String name;
    @Column(name="funcionario_age")
    private int age;
    @Column(name="funcionario_birthday")
    private Date birthday;
    @Column(name="funcionario_document", length=50)
    private String document;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    public String getDocument() {
        return document;
    }
    public void setDocument(String document) {
        this.document = document;
    }

}
