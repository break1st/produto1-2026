insert into tb_categoria (nome, criado_em) values ('Livros', NOW());
insert into tb_categoria (nome, criado_em) values ('Notebookes', NOW());
insert into tb_categoria (nome, criado_em) values ('Computadores', NOW());
insert into tb_categoria (nome, criado_em) values ('Brinquedos', NOW());

insert into tb_produto (nome, descricao, preco, img_url, criado_em) values('Brinquedo 02','descrição do produto 02', 20.00, 'http://localhost:8080/prod02.jpg', NOW());
insert into tb_produto (nome, descricao, preco, img_url, criado_em) values('Brinquedo 01','descrição do produto 01', 10.00, 'http://localhost:8080/prod01.jpg', NOW());
insert into tb_produto (nome, descricao, preco, img_url, criado_em) values('Brinquedo 03','descrição do produto 03', 30.00, 'http://localhost:8080/prod03.jpg', NOW());
insert into tb_produto (nome, descricao, preco, img_url, criado_em) values('Brinquedo 04','descrição do produto 04', 40.00, 'http://localhost:8080/prod04.jpg', NOW());
insert into tb_produto (nome, descricao, preco, img_url, criado_em) values('Brinquedo 05','descrição do produto 05', 50.00, 'http://localhost:8080/prod05.jpg', NOW());

insert into tb_perfil (nome) values ('ROLE_ADMINISTRADOR');
insert into tb_perfil (nome) values ('ROLE_VENDEDOR');
insert into tb_perfil (nome) values ('ROLE_CLIENTE');

insert into tb_usuario(nome, telefone, email, senha, criado_em) values('Joao', '3799999-9999', 'joao@email.com', '123456', NOW());
insert into tb_usuario(nome, telefone, email, senha, criado_em) values('Maria', '3799999-9999', 'mariae@email.com', '123456', NOW());