INSERT INTO produto (descricao,unidade_medida) VALUES ("Salgadinho Cheetos","UN");
INSERT INTO produto (descricao,unidade_medida) VALUES ("Batata","KG");
INSERT INTO produto (descricao,unidade_medida) VALUES ("Banana","DZ");
INSERT INTO fornecedor (cnpj,email,nome,telefone,bairro,cep,municipio,complemento,logradouro,estado,numero) VALUES ("01031503000197","pepsico@pepsico.com.br","PEPSICO LTDA", "119957516492","Distrito Industrial","18120000","Mairinque","LOTE 1","Av. Brasil","SP","363");
INSERT INTO fornecedor (cnpj,email,nome,telefone,bairro,cep,municipio,complemento,logradouro,estado,numero) VALUES ("95325425000187","yoki@yoki.com","YOKI LTDA", "119957367791","Jardim Vitoria","18120000","Mairinque","QUADRA 13","Av. Lobato","SP","413");
INSERT INTO estoque (id,produto_id,quantidade,valor_venda) VALUES (1,1,10,10);
INSERT INTO estoque (id,produto_id,quantidade,valor_venda) VALUES (2,2,10,20);
INSERT INTO estoque (id,produto_id,quantidade,valor_venda) VALUES (3,3,10,30);
INSERT INTO cliente (cpf,email,nome,telefone,bairro,cep,municipio,complemento,logradouro,estado,numero) VALUES ("48699576803","carlos.marangoni1@gmail.com","Carlos Marangoni", "11997516492","Nova Mairinque","18120000","Mairinque","Casa","Rua das flores","SP","363");
INSERT INTO cliente (cpf,email,nome,telefone,bairro,cep,municipio,complemento,logradouro,estado,numero) VALUES ("35919484896","marangoni.darlan1@gmail.com","Darlan Marangoni", "11997515666","Reneville","18120000","Reneville","Casa","Av. Reneville","SP","159");


