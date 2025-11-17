package projeto_garcom.com.demo.cozinha;

import jakarta.persistence.*;
import lombok.*;
import projeto_garcom.com.demo.usuario.UsuarioEntity;

@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cozinha", schema = "restaurante")
public class CozinhaEntity extends UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}