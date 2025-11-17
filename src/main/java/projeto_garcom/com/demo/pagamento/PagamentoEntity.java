package projeto_garcom.com.demo.pagamento;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pagamento", schema = "restaurante")
public class PagamentoEntity {

}