package ru.miroshka.hw2.data;

//import jakarta.persistence.*;
import lombok.Data;
import javax.persistence.*;


@Entity
@Data
@Table(name="authority")
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private  String name;
}

