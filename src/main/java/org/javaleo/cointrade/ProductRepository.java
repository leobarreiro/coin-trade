package org.javaleo.cointrade;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query("select p from Product p where p.name like %?1%")
	List<Product> findByName(String name);

}
