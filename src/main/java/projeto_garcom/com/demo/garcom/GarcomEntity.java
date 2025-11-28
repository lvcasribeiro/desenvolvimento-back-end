package projeto_garcom.com.demo.garcom;

import jakarta.persistence.*;
import lombok.*;
import projeto_garcom.com.demo.usuario.UsuarioEntity;

@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GarcomEntity extends UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}