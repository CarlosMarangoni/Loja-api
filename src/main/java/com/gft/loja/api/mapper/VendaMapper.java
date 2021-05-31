package com.gft.loja.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gft.loja.api.model.VendaModel;
import com.gft.loja.domain.model.Venda;

@Component
public class VendaMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	public VendaModel toModel(Venda venda) {
		
		return  modelMapper.map(venda, VendaModel.class);
		
	}
	
	public List<VendaModel> toCollectionModel(List<Venda> vendas){
		return vendas.stream().map(this::toModel)
		.collect(Collectors.toList());
	}
}
