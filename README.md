# DesafioUbiwhere

Desafio Ubiwhere "agenda telefónica"

Para este desafio foi utilizado o Wildfly 8.2.1 e o Mysql.

Para utilizar este projecto é necessário configurar a pool connection para o Mysql e alterar, respectivamente, os campos no ficheiro persistence.xml.

Existe uma main class onde são feitos os seguintes aspectos:

> Adicionado um conjunto de entidades;

> Alterado o valor dessas entidades;

> Remoção das entidades;

## Arquitectura do modelo de dados
A arquitectura do modelos de dados é relativamente simples sendo que a sua geração foi realizada automaticamente pelo hibernate, tendo por base o seguinte modelo de dados: 

Existe uma entidade principal que pode conter os seguintes aspectos:
> Localização da entidade (por exemplo Aveiro);

> Nome da entidade;

> Codigo Postal;

> Endereço de morada;

>> Morada completa;

>> Numero da casa;

> Contactos;

>> Tipo de contacto (email, telefone, telemóvel);

>> Contacto;

## Estrutura da solução

A solução está divida em 5 partes:

* Módulo dataBase (ubiwhere.example.desafio.dataBase);
> Contêm as classes que definem a estrutura de dados.

* Módulo Manager (ubiwhere.example.desafio.manager);
> Contêm a class onde é realizada as operações básicas da agenda. 

* Módulo restAPI (ubiwhere.example.desafio.restAPI);
> Contêm a class onde está definida a API utilizada nesta solução.

* Módulo utils (ubiwhere.example.desafio.utils);
> Contêm as classes que ajudam na construção da solução.

* Módulo mainTests(ubiwhere.example.desafio.mainTests);
> Contêm uma class simples para testes usando a API definida.

## Definição dos Endpoints

A definição dos endpoints pode ser consultada aqui : <http://docs.desafioubiwhere.apiary.io/>







