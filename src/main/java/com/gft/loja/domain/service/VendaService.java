package com.gft.loja.domain.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gft.loja.domain.exception.ItemBodyViolationException;
import com.gft.loja.domain.model.Estoque;
import com.gft.loja.domain.model.Usuario;
import com.gft.loja.domain.model.Venda;
import com.gft.loja.domain.model.enumeration.StatusVenda;
import com.gft.loja.domain.repository.VendaRepository;

@Service
public class VendaService {

	@Autowired
	private VendaRepository vendaRepository;
	
	@Autowired
	private EstoqueService estoqueService;

	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private SenderMailService senderMailService;

	
	
	public List<Venda> listar() {
		return vendaRepository.findAll();
	}
	
	public List<Venda> listarComFiltroCliente(Long clienteId) {
		return vendaRepository.findByClienteId(clienteId);
	}
	
	public List<Venda> listarComFiltroStatusVenda(String statusVenda) {
		StatusVenda statusEnum = StatusVenda.getEnum(statusVenda);
		
		return vendaRepository.findByStatusVenda(statusEnum);
	}


	public Venda buscar(Long id) {
		return vendaRepository.findById(id).get();

	}

	@Transactional
	public Venda salvar(Venda venda) {
		AtomicInteger atomicSum = new AtomicInteger(0);
		Usuario usuarioLogado = (Usuario) SecurityContextHolder.getContext().getAuthentication() //Buscar usuario logado
                .getPrincipal();
		try {
			venda.getItensVenda().forEach(i -> {

				if (i.getItensVendaPK().getProduto() == null) {
					throw new ItemBodyViolationException(
							"Código do produto inválido. Faça o preenchimento correto e tente novamente.");
				}

				Estoque estoque = estoqueService.buscar(i.getItensVendaPK().getProduto().getId());
				i.getItensVendaPK().setProduto(estoque.getProduto());
				estoque.subtraiQuantidadeProduto(i.getQuantidade());//Subtrai as quantidades da venda pela quantidade do estoque
				
				i.setValorVenda(estoque.calculaVenda(i.getQuantidade()));// Calcula preço final de venda do item
				i.getItensVendaPK().setVenda(venda);
				i.setItem(atomicSum.incrementAndGet());
				
				
			});
			venda.setCliente(clienteService.buscar(usuarioLogado.getId())); //Determina o cliente com base no usuario logado
			venda.setDataVenda(OffsetDateTime.now());
			venda.setCliente(clienteService.buscar(venda.getCliente().getId()));
			venda.setStatusVenda(StatusVenda.PENDENTE);
		} catch (NoSuchElementException e) {
			throw new ItemBodyViolationException(
					e.getMessage());
		}
		
		senderMailService.enviar(venda);
		return vendaRepository.save(venda);
	}

	public Venda atualizarStatusEntregaRecebido(Long id) {
		Venda venda = buscar(id);
		venda.setStatusVenda(StatusVenda.RECEBIDO);
		vendaRepository.save(venda);
		return venda;
	}

	

	
}
