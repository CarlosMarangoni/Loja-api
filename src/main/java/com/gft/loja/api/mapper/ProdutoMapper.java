package com.gft.loja.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gft.loja.api.model.ProdutoModel;
import com.gft.loja.domain.model.Produto;

@Component
public class ProdutoMapper {

	@Autowired
	private ModelMapper modelMapper;

	public ProdutoModel toModel(Produto produto) {

		return modelMapper.map(produto, ProdutoModel.class);

	}

	public List<ProdutoModel> toCollectionModel(List<Produto> produtos) {
		return produtos.stream().map(this::toModel).collect(Collectors.toList());
	}
}
