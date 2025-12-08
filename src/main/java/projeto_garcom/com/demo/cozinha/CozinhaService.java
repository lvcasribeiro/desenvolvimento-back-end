package projeto_garcom.com.demo.cozinha;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import projeto_garcom.com.demo.common.exceptions.InvalidEntityException;
import projeto_garcom.com.demo.common.exceptions.NotFoundException;
import projeto_garcom.com.demo.pedido.PedidoEntity;
import projeto_garcom.com.demo.pedido.PedidoMapper;
import projeto_garcom.com.demo.pedido.PedidoRepository;
import projeto_garcom.com.demo.pedido.StatusPedido;
import projeto_garcom.com.demo.pedido.dto.PedidoResponseDTO;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class CozinhaService {

    private final PedidoRepository pedidoRepository;
    private final PedidoMapper pedidoMapper;

    public List<PedidoEntity> listarPedidosPendentes() {
        return pedidoRepository.findByStatus(StatusPedido.RECEBIDO);
    }

    public List<PedidoEntity> listarPedidosEmPreparo() {
        return pedidoRepository.findByStatus(StatusPedido.EM_PREPARO);
    }

    public List<PedidoEntity> listarPedidosProntos() {
        return pedidoRepository.findByStatus(StatusPedido.PRONTO);
    }

    @Transactional
    public PedidoResponseDTO iniciarPreparo(Long pedidoId) {
        PedidoEntity pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new NotFoundException("Pedido não encontrado"));

        if (pedido.getStatus() != StatusPedido.RECEBIDO) {
            throw new NotFoundException("Pedido não está disponível para iniciar preparo");
        }

        pedido.setStatus(StatusPedido.EM_PREPARO);

        return pedidoMapper.toResponse(pedidoRepository.save(pedido));
    }

    @Transactional
    public PedidoResponseDTO finalizarPreparo(Long pedidoId) {
        PedidoEntity pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new NotFoundException("Pedido não encontrado"));

        if (pedido.getStatus() != StatusPedido.EM_PREPARO) {
            throw new InvalidEntityException("Pedido não está em preparo");
        }

        pedido.setStatus(StatusPedido.PRONTO);
        pedido.setHorarioEntrega(LocalDateTime.now());

        return pedidoMapper.toResponse(pedidoRepository.save(pedido));
    }
}
