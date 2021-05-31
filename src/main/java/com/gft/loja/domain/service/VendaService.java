package com.gft.loja.domain.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.gft.loja.api.model.EnderecoResumoModel;
import com.gft.loja.api.model.VendaModel;
import com.gft.loja.domain.exception.ItemBodyViolationException;
import com.gft.loja.domain.model.Estoque;
import com.gft.loja.domain.model.Venda;
import com.gft.loja.domain.model.enumeration.StatusVenda;
import com.gft.loja.domain.repository.EstoqueRepository;
import com.gft.loja.domain.repository.VendaRepository;

@Service
public class VendaService {

	@Autowired
	private VendaRepository vendaRepository;
	
	@Autowired
	private EstoqueService estoqueService;

	@Autowired
	private ClienteService clienteService;

	public List<Venda> listar() {
		return vendaRepository.findAll();
	}

	public Venda buscar(Long id) {
		return vendaRepository.findById(id).get();

	}

	@Transactional
	public Venda salvar(Venda venda) {
		AtomicInteger atomicSum = new AtomicInteger(0);
		try {
			venda.getItensVenda().forEach(i -> {

				if (i.getItensVendaPK().getProduto() == null) {
					throw new ItemBodyViolationException(
							"Código do produto inválido. Faça o preenchimento correto e tente novamente.");
				}

				Estoque estoque = estoqueService.buscar(i.getItensVendaPK().getProduto().getId());//Subtrai as quantidades da venda pela quantidade do estoque
				i.getItensVendaPK().setProduto(estoque.getProduto());
				estoque.subtraiQuantidadeProduto(i.getQuantidade());
				
				i.setValorVenda(estoque.calculaVenda(i.getQuantidade()));// Calcula preço final de venda do item e
				i.getItensVendaPK().setVenda(venda);
				i.setItem(atomicSum.incrementAndGet());
				
				
			});
			venda.setDataVenda(OffsetDateTime.now());
			venda.setCliente(clienteService.buscar(venda.getCliente().getId()));
			venda.setStatusVenda(StatusVenda.PENDENTE);
		} catch (NoSuchElementException e) {
			throw new ItemBodyViolationException(
					e.getMessage());
		}

		return vendaRepository.save(venda);
	}

	public Venda atualizarStatusEntregaRecebido(Long id) {
		Venda venda = buscar(id);
		venda.setStatusVenda(StatusVenda.RECEBIDO);
		vendaRepository.save(venda);
		return venda;
	}

}
