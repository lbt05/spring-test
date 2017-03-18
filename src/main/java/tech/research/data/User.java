package tech.research.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by jay on 6/03/17.
 */
@Builder
@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="user")
public class User implements Serializable{

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String username;

    @Column
    private int age;

    @Column
    @JsonIgnore
    private String password;
}
