# coin-trade
API Rest com endpoints do mercado de criptomoedas, com ênfase em exchanges brasileiras.

### Endpoints da API

* **exchange/list**
Retorna todas as exchanges monitoradas pelo coin-trade

>http://javaleo.org:7071/exchange/list

* **market/[nome_exchange]**
Retorna todos os mercados trabalhados pela Exchange
Exemplo:
>http://javaleo.org:7071/market/braziliex

* **candle/[nome_exchange]/[nome_mercado]/[intervalo]**
Retorna todos os candle-sticks de um determinado mercado
 Exemplo:
>http://javaleo.org:7071/candle/braziliex/ethbrl/min30

