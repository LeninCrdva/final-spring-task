package tecazuay.eduv.finalspringtask.models.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;

@Entity
@Data
public class Ticket implements Serializable {

    @Id
    private Long codeTicket;

    private String title;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String description;

    private Boolean state;

    @Temporal(TemporalType.DATE)
    private Date createAt;

    @ManyToOne
    @JoinColumn(name = "code_case", referencedColumnName = "codeCase")
    private Ticket_Case ticketCase;

    @ManyToOne
    @JoinColumn(name = "code_department", referencedColumnName = "codeDepartment")
    private Department codeDepartment;

    @ManyToOne
    @JoinColumn(name = "id_card", referencedColumnName = "idCard")
    private User userIdCard;

    @PrePersist
    public void prePersist() {
        state = Boolean.TRUE;
        createAt = new Date();
        codeTicket = generarNumeroAleatorioDe5Cifras();
    }

    private Long generarNumeroAleatorioDe5Cifras() {
        Random random = new Random();
        return (long) (10000 + random.nextInt(90000));
    }

}
