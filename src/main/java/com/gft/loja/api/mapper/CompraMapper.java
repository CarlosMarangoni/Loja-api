package com.gft.loja.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gft.loja.api.model.CompraModel;
import com.gft.loja.domain.model.Compra;

@Component
public class CompraMapper {

	@Autowired
	private ModelMapper modelMapper;

	public CompraModel toModel(Compra compra) {

		return modelMapper.map(compra, CompraModel.class);

	}

	public List<CompraModel> toCollectionModel(List<Compra> compras) {
		return compras.stream().map(this::toModel).collect(Collectors.toList());
	}
}
