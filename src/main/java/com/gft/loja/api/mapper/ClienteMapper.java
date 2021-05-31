package com.gft.loja.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gft.loja.api.model.ClienteModel;
import com.gft.loja.domain.model.Cliente;

@Component
public class ClienteMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	public ClienteModel toModel(Cliente cliente) {
		
		return  modelMapper.map(cliente, ClienteModel.class);
		
	}
	
	public List<ClienteModel> toCollectionModel(List<Cliente> clientes){
		return clientes.stream().map(this::toModel)
		.collect(Collectors.toList());
	}
}
