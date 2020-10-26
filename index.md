# Bizu API

API para ajudar a conhecer melhor a seus Candidatos

## Geral

- Todos os endpoints sempre retornaram a mesma estrutura de dados, mostrando as seguintes informações:

```json
{
  "id": "5e8cdcfc6f75087c649eaa94", //identificador
  "anoEleicao": 2016,
  "bens": [],
  "cargoEleicao": "VEREADOR",
  "cassacoes": [
    {
      "codigoCandidato": "60000010232",
      "motivoCanssacao": "Ausência de requisito de registro "
    }
  ],
  "codigoCandidato": "60000010232",
  "coligacao": "TRABALHANDO COM O POVO",
  "eleito": false,
  "estadoEleicao": "CE",
  "municipioEleicao": "CAUCAIA",
  "nomeCandidato": "ADRIANA FERREIRA DE BRITO",
  "nomeCandidatoNaUrna": "ADRIANA BRITO",
  "numeroEleicao": 11121,
  "partido": "PP",
  "statusCandidatura": "INDEFERIDO",
  "urlFoto": "https://gazetadopovo-candidatos-2016.s3.amazonaws.com/fotos/ce/caucaia/adriana-brito-11121.jpg"
}

```

| Campo | Descrição |
|-------|-----------|
|id     | Identificador dentro do bizu|
|bens   | Lista com todos os bens declarados nesse ano eleitoral |
|codigoCandidato| Código do candidato dentro do TSE |


- Todos os end-points são paginados em 15 itens.

- Todos os end-points teram os mesmo filtros disponíveis segue abaixo:

| Filtro | Tipo |
|--------|------|
| nomeCandidato | String |
| cargo         | String |
| cassacao      | boolean |
| page          | int  |


## Candidatos

> GET /candidatos

Lista com todos os candidatos paginados em 15;


## Apartir de um Ano Eleitoral

> GET /candidatos/2020

Lista com todos os candidatos em 2020 paginados em 15.

## Apartir de um Ano Eleitoral e Estado

> GET /candidatos/2020/CE

Lista com todos os candidatos do Ceará em 2020 paginados em 15.

## Apartir de um Ano Eleitoral, Estado e Município

> GET /candidatos/2020/CE/FORTALEZA

Lista com todos os candidatos do **Ceará** no Município de **Fortaleza** em **2020** paginados em 15.

