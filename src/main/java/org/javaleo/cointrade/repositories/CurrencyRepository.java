package org.javaleo.cointrade.repositories;

import org.javaleo.cointrade.entities.Currency;
import org.javaleo.cointrade.enums.Symbol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {

	Currency findOneBySymbol(Symbol symbol);

}
