package ma.rightman.openapidemo.entity;

import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name="student")
public class StudentEntity {

    @Id
    @Column(name="id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "tag")
    private String tag;
}
