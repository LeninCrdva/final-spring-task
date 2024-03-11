package tecazuay.eduv.finalspringtask.models.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserDTO implements Serializable {
    private String idcard;
    private String password;
}
