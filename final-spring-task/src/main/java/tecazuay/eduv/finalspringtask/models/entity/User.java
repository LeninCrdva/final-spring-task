package tecazuay.eduv.finalspringtask.models.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
public class User implements Serializable {

    @Id
    private String idCard;

    private String name;

    private String lastName;

    private String email;

    private String phone;

    private String password;

    @ManyToOne
    @JoinColumn(name = "codeCity")
    private City codeCity;
}
