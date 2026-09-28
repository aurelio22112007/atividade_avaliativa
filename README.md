# atividade_avaliativSistema de Gerenciamento de Boxes e Ordens de Serviço

Descrição

Sistema desenvolvido em Java para gerenciamento de uma oficina mecânica, permitindo cadastrar ordens de serviço, associar mecânicos aos boxes e controlar as ordens atribuídas a cada box.

Funcionalidades

* Cadastrar ordem de serviço
* Associar mecânico a um box
* Atribuir ordem de serviço a um box
* Exibir todas as ordens de um box
* Consultar quantidade de ordens finalizadas por box
* Buscar ordens por status
* Exibir detalhes completos de uma ordem

Regras de Negócio

* Um mecânico pode ser responsável por apenas um box.
* Um box pode possuir várias ordens de serviço.
* A capacidade máxima de cada box deve ser respeitada.
* Ordens abertas não possuem box atribuído.
* O tipo do serviço deve ser compatível com o tipo do box.
* Uma ordem não pode ser atribuída a mais de um box.
* Ordens finalizadas mantêm a informação do box utilizado.
* O box não precisa manter uma ordem que já foi finalizada.

Estrutura do Sistema

Mecânico

Possui:

* Nome
* CPF
* Especialidade
* Telefone
* Box responsável

Box

Possui:

* Número
* Tipo de serviço
* Capacidade máxima
* Localização
* Mecânico responsável
* Ordens de serviço

Ordem de Serviço

Possui:

* Código
* Cliente
* Modelo do veículo
* Placa
* Data
* Status
* Valor estimado
* Box
* Serviços

Serviço

Possui:

* Nome
* Tempo estimado
* Valor
* Categoria

Execução

O programa é executado pela classe:

Main

Ao iniciar, são criados automaticamente:

* 3 mecânicos
* 3 boxes

O usuário pode acessar as funcionalidades através do menu apresentado no console.

Tecnologias

* Java
* Programação Orientada a Objetos
* ArrayList
* Scanner
* Estruturas de decisão e repetição
