package ru.miroshka.market.core.data;

//import jakarta.persistence.*;
import javax.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;
}
