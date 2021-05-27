INSERT INTO produto (descricao,unidade_medida) VALUES ("Salgadinho Cheetos","UN");
INSERT INTO produto (descricao,unidade_medida) VALUES ("Batata","KG");
INSERT INTO produto (descricao,unidade_medida) VALUES ("Banana","DZ");
INSERT INTO fornecedor (cnpj,email,nome,telefone,bairro,cep,municipio,complemento,logradouro,estado,numero) VALUES ("01031503000197","pepsico@pepsico.com.br","PEPSICO LTDA", "119957516492","Distrito Industrial","18120000","Mairinque","LOTE 1","Av. Brasil","SP","363");
INSERT INTO fornecedor (cnpj,email,nome,telefone,bairro,cep,municipio,complemento,logradouro,estado,numero) VALUES ("95325425000187","yoki@yoki.com","YOKI LTDA", "119957367791","Jardim Vitoria","18120000","Mairinque","QUADRA 13","Av. Lobato","SP","413");
INSERT INTO estoque (id,produto_id,quantidade,valor_venda) VALUES (1,1,0,0);
INSERT INTO estoque (id,produto_id,quantidade,valor_venda) VALUES (2,2,0,0);
INSERT INTO estoque (id,produto_id,quantidade,valor_venda) VALUES (3,3,0,0);
INSERT INTO cliente (cpf,email,nome,telefone,bairro,cep,municipio,complemento,logradouro,estado,numero) VALUES ("48699576803","carlos.marangoni1@gmail.com","Carlos Marangoni", "11997516492","Nova Mairinque","18120000","Mairinque","Casa","Rua das flores","SP","363");
INSERT INTO cliente (cpf,email,nome,telefone,bairro,cep,municipio,complemento,logradouro,estado,numero) VALUES ("35919484896","marangoni.darlan1@gmail.com","Darlan Marangoni", "11997515666","Reneville","18120000","Reneville","Casa","Av. Reneville","SP","159");
INSERT INTO compra (data_compra,fornecedor_id) VALUES ("2021-10-10",1);
INSERT INTO compra (data_compra,fornecedor_id) VALUES ("2021-09-09",2); 
INSERT INTO itens_compra (item,compra_id,produto_id,quantidade,valor_compra) VALUES (1,1,3,5,136.22);
INSERT INTO itens_compra (item,compra_id,produto_id,quantidade,valor_compra) VALUES (2,1,1,2,126.22);
INSERT INTO itens_compra (item,compra_id,produto_id,quantidade,valor_compra) VALUES (3,1,2,1,16);
INSERT INTO itens_compra (item,compra_id,produto_id,quantidade,valor_compra) VALUES (1,2,1,3,22);
INSERT INTO itens_compra (item,compra_id,produto_id,quantidade,valor_compra) VALUES (2,2,2,4,122);

