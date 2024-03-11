package tecazuay.eduv.finalspringtask.models.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
public class Company implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codeCompany;

    private String name;

    private String phone;

    @ManyToOne
    @JoinColumn(name = "code_city", referencedColumnName = "codeCity")
    private City codeCity;
}
