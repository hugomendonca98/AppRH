package com.AppRH.AppRH.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.AppRH.AppRH.models.Empresa;

public interface EmpresaRepository extends CrudRepository<Empresa, Long> {
	
	Empresa findById(long id);
	Empresa findByNome(String nome);
	
	// Query para a busca
	@Query(value = "select u from Empresa u where u.nome like %?1%")
	List<Empresa>findByNomes(String nome);

}
