package projeto_garcom.com.demo.caixa;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import projeto_garcom.com.demo.usuario.UsuarioEntity;

@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "caixa", schema = "restaurante")
public class CaixaEntity extends UsuarioEntity {

}