package projeto_garcom.com.demo.garcom;

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
@Table(name = "garcom", schema = "restaurante")
public class GarcomEntity extends UsuarioEntity {

}