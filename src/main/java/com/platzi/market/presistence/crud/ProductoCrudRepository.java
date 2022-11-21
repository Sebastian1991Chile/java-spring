package com.platzi.market.presistence.crud;

import java.util.List;
import java.util.Optional;

//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.platzi.market.presistence.entity.Producto;

public interface ProductoCrudRepository extends CrudRepository<Producto, Integer>{
	
	//@Query(value = "SELECT * FROM productos WHERE id_categoria = ?", nativeQuery = true)
	List<Producto> findByIdCategoriaOrderByNombreAsc(int idCategoria);

	Optional<List<Producto>> findByCantidadStockLessThanAndEstado(int cantidadStock, boolean estado);

}
