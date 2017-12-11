# ufjf-dcc196-2017-3-trb3-andre-trab3
ufjf-dcc196-2017-3-trb3-andre-trab3 created by GitHub Classroom

UFJF - DCC196-2017 - Trabalho 3

Aluno: André Magno Ribeiro

Matricula: 201376060

Email: andremagno06@gmail.com

Descrição o projeto consiste em um aplicativo de organização pessoal onde ser pode cadastrar Tarefas podendo inserir, alterar consultar e excluir, e para cada tarefa podemos ter
Tags onde podemos ser associadas a uma ou mais tarefa.

Modelo de Banco de Dados Utilizado:


Foi utilizado tres tabelas que são: Tarefas, tag, Relacao , onde os atributos são:

Tarefas
	id  -  int chave primaria
	Titulo Varchar
	Descrição	Varchar
	Dificuldade  Inteiro
	Estado Varchar Varchar

Tag
	id  -  int chave primaria
	nomeTag Varchar

Relacao
	id  -  int chave primaria
	tag - chave secundaria
	Relacao - chave secundaria 
	
Esta tabela 'Relacao' tem por objetivo fazer a ligação de n* para n* de Tarefa para Tag.
