package tecazuay.eduv.finalspringtask.models.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
public class Attached_file implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codeFile;

    private String fileName;

    @ManyToOne
    @JoinColumn(name = "code_ticket", referencedColumnName = "codeTicket")
    private Ticket codeTicket;
}
