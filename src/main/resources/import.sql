INSERT INTO produto (descricao,unidade_medida) VALUES ("Salgadinho Cheetos","UN");
INSERT INTO produto (descricao,unidade_medida) VALUES ("Batata","KG");
INSERT INTO produto (descricao,unidade_medida) VALUES ("Banana","DZ");
INSERT INTO fornecedor (cnpj,email,nome,telefone,bairro,cep,municipio,complemento,logradouro,estado,numero) VALUES ("01031503000197","pepsico@pepsico.com.br","PEPSICO LTDA", "119957516492","Distrito Industrial","18120000","Mairinque","LOTE 1","Av. Brasil","SP","363");
INSERT INTO fornecedor (cnpj,email,nome,telefone,bairro,cep,municipio,complemento,logradouro,estado,numero) VALUES ("95325425000187","yoki@yoki.com","YOKI LTDA", "119957367791","Jardim Vitoria","18120000","Mairinque","QUADRA 13","Av. Lobato","SP","413");
INSERT INTO estoque (id,produto_id,quantidade,valor_venda) VALUES (1,1,10,10);
INSERT INTO estoque (id,produto_id,quantidade,valor_venda) VALUES (2,2,10,20);
INSERT INTO estoque (id,produto_id,quantidade,valor_venda) VALUES (3,3,10,30);
INSERT INTO perfil (descricao) VALUES ('LOJA');
INSERT INTO perfil (descricao) VALUES ('CLIENTE');
INSERT INTO usuario (dtype,email,nome,senha) VALUES ("Usuario","loja@loja.com","Loja","$2a$10$2KAjNF9hMFp8s6ZEkikonutmLmB.mKJ0s.VVHHu4e3UiKaed3BurS");
INSERT INTO usuario (dtype,email,nome,senha,cpf,bairro,cep,complemento,estado,logradouro,municipio,numero,telefone) VALUES ("Cliente","cliente@cliente.com","Cliente","$2a$10$2KAjNF9hMFp8s6ZEkikonutmLmB.mKJ0s.VVHHu4e3UiKaed3BurS","48699576803","Nova Mairinque","18120000","Casa","SP","Rua Antonio Francisco Lisboa","Mairinque","363","11997516492");
INSERT INTO usuario_perfis (usuario_id,perfis_id) VALUES (1,1);
INSERT INTO usuario_perfis (usuario_id,perfis_id) VALUES (2,2);

