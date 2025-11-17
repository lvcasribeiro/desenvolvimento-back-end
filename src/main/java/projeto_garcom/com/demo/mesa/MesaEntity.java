package projeto_garcom.com.demo.mesa;

import jakarta.persistence.*;
import lombok.*;
import projeto_garcom.com.demo.garcom.GarcomEntity;

@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "mesa", schema = "restaurante")
public class MesaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero", nullable = false)
    private Integer numero;

    @Column(name = "disponivel", nullable = false)
    private Boolean disponivel;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "garcom_id")
    private GarcomEntity garcom;
}