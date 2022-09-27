
package model;

import java.util.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Perfil {
    private int idPerfil;
    private String nome;
    private Date dataCadastro;
    private int status;
    
}
