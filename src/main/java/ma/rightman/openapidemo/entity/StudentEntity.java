package ma.rightman.openapidemo.entity;

import javax.persistence.*;

@Entity
@Table(name="student")
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "tag")
    private String tag;

    @Column(name = "age")
    private Integer age;

    public StudentEntity() {
        this.id = null;
        this.name = null;
        this.tag = null;
        this.age = null;
    }

    public StudentEntity(Integer id, String name, String tag, Integer age) {
        this.id = id;
        this.name = name;
        this.tag = tag;
        this.age = age;
    }

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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
