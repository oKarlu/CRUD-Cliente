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
public class Cliente {
    private Integer idCliente;
    private String nome;
    private String cpf;
    private String endereco;
    private String email;
    private String telefone;
    private Integer status;
    private Date dataCadastro;
    
}
