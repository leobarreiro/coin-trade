# COIN-TRADE API-REST de Exchanges Brasileiras de Cryptocoins 

API Rest com endpoints do mercado de cryptocoins, com ênfase em exchanges brasileiras.


### Endpoints da API

* **exchange/list**
Retorna todas as exchanges monitoradas pelo coin-trade
>http://javaleo.org:7071/exchange/list


* **market/list/[nome_exchange]**
*Retorna todos os mercados trabalhados pela Exchange*
Exemplo:
>http://javaleo.org:7071/market/list/braziliex


* **candle/[nome_exchange]/[nome_mercado]/[intervalo]**
Retorna todos os candle-sticks de um determinado mercado
 Exemplo:
>http://javaleo.org:7071/candle/braziliex/ethbrl/min30
