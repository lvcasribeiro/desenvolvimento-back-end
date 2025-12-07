package projeto_garcom.com.demo.cozinha;

import jakarta.persistence.*;
import lombok.*;
import projeto_garcom.com.demo.pedido.PedidoEntity;

import java.util.List;

@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cozinha", schema = "restaurante")
public class CozinhaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "cozinha", fetch = FetchType.LAZY)
    private List<PedidoEntity> pedidos;
}