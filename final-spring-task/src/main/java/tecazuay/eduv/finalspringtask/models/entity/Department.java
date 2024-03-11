package tecazuay.eduv.finalspringtask.models.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
public class Department implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codeDepartment;

    private String name;

    @ManyToOne
    @JoinColumn( referencedColumnName = "codeCompany", name = "code_company")
    private Company codeCompany;
}
