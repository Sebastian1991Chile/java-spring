package com.platzi.market.presistence.crud;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.platzi.market.presistence.entity.Compra;

public interface CompraCrudRepository extends CrudRepository<Compra, Integer> {

	Optional<List<Compra>> findByIdCliente(String idCliente);
}
