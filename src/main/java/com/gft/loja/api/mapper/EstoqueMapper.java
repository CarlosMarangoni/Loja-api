package com.gft.loja.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gft.loja.api.model.EstoqueModel;
import com.gft.loja.api.model.EstoqueResumoModel;
import com.gft.loja.domain.model.Estoque;

@Component
public class EstoqueMapper {

	@Autowired
	private ModelMapper modelMapper;

	public EstoqueModel toModel(Estoque estoque) {

		return modelMapper.map(estoque, EstoqueModel.class);

	}

	public EstoqueResumoModel toModelResumo(Estoque estoque) {

		return modelMapper.map(estoque, EstoqueResumoModel.class);

	}

	public List<EstoqueModel> toCollectionModel(List<Estoque> estoques) {
		return estoques.stream().map(this::toModel).collect(Collectors.toList());
	}

	public List<EstoqueResumoModel> toCollectionModelResumo(List<Estoque> estoques) {
		return estoques.stream().map(this::toModelResumo).collect(Collectors.toList());
	}
}
