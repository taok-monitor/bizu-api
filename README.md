# bizu-api

Api tem o objetivo de entregar um endpoint informando todos os candidatos que tiveram suas candidaturas cassadas por algum motivo.

**Motivação**

Hoje existe algumas plataformas para acompanharmos os passos de nossos candidatos, mas essa tem o objeito principal de destacar os candidatos que um dia tiveram algum problema com suas candidaturas, informando os motivos e ajudando os eleitores a escolherem seus representantes.

Infelizmente temos a informação sobre cassações apenas de 2016 e 2018.

**Arquitetura**

Em nossa versão atual é baixado alguns arquivos ZIP no portal do TSE no link http://www.tse.jus.br/eleicoes/estatisticas/repositorio-de-dados-eleitorais-1/repositorio-de-dados-eleitorais e feito uma carga desses dados para nossa base de dados.

Nesse processo e baixado 3 arquivos zip:

- Candidatos
- Bens de candidatos
- Motivo da cassação
