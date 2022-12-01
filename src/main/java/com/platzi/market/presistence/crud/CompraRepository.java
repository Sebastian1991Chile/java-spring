package com.platzi.market.presistence.crud;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.platzi.market.domain.dto.Purchase;
import com.platzi.market.domain.repository.PurchaseRepository;
import com.platzi.market.presistence.entity.Compra;
import com.platzi.market.presistence.mapper.PurchaseMapper;

@Repository
public class CompraRepository implements PurchaseRepository {

	@Autowired
	CompraCrudRepository compraCrudRepository;
	
	@Autowired
	PurchaseMapper mapper;
	
	@Override
	public List<Purchase> getAll() {
		return mapper.toPurchases((List<Compra>) compraCrudRepository.findAll());
	}

	@Override
	public Optional<List<Purchase>> getByClient(String clientId) {
		// TODO Auto-generated method stub
		return compraCrudRepository.findByIdCliente(clientId)
				.map(compras -> mapper.toPurchases(compras));
	}

	@Override
	public Purchase save(Purchase purchase) {
		Compra compra = mapper.toCompra(purchase);
		compra.getProductos().forEach(producto -> producto.setCompra(compra));
		return mapper.toPurchase(compraCrudRepository.save(compra));
	}

}
