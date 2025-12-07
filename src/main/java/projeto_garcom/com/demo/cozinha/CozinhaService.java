package projeto_garcom.com.demo.cozinha;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import projeto_garcom.com.demo.pedido.PedidoEntity;
import projeto_garcom.com.demo.pedido.PedidoRepository;
import projeto_garcom.com.demo.pedido.StatusPedido;

import java.util.List;

@Service
@AllArgsConstructor
public class CozinhaService {

    private final PedidoRepository pedidoRepository;

    public List<PedidoEntity> listarPedidosPendentes() {
        return pedidoRepository.findByStatus(StatusPedido.RECEBIDO);
    }

    public List<PedidoEntity> listarPedidosEmPreparo() {
        return pedidoRepository.findByStatus(StatusPedido.EM_PREPARO);
    }

    public List<PedidoEntity> listarPedidosProntos() {
        return pedidoRepository.findByStatus(StatusPedido.PRONTO);
    }
}
