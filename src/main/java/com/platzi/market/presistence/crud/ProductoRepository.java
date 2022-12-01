package com.platzi.market.presistence.crud;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.platzi.market.domain.dto.Product;
import com.platzi.market.domain.repository.ProductRepository;
import com.platzi.market.presistence.entity.Producto;
import com.platzi.market.presistence.mapper.ProductMapper;

@Repository
public class ProductoRepository implements ProductRepository{
	
	@Autowired
	ProductoCrudRepository productoCrudRepository;
	
	@Autowired
	ProductMapper mapper;
	
	@Override
	public List<Product> getAll(){
		List<Producto> productos = (List<Producto>) productoCrudRepository.findAll();
		return mapper.toProducts(productos);
	}
	
	@Override
	public Optional <Product> getProduct (int idProducto){
		return productoCrudRepository.findById(idProducto).map(prod -> mapper.toProduct(prod));
	}
	
	@Override
	public void delete(int productId) {
		productoCrudRepository.deleteById(productId);
	}
	
	@Override
	public Optional<List<Product>> getByCategory(int categoryId) {
		List<Producto> productos = productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
		return Optional.of(mapper.toProducts(productos));
	}

	@Override
	public Optional<List<Product>> getScarseProducts(int quantity) {
		Optional<List<Producto>> productos = productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity, true);
		return productos.map(prods -> mapper.toProducts(prods));
	}

	@Override
	public Product save(Product product) {
		Producto producto = mapper.toProducto(product);
		return mapper.toProduct(productoCrudRepository.save(producto));
	}
}
