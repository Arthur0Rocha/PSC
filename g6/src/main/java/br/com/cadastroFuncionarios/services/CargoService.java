package br.com.cadastroFuncionarios.services;

import java.util.List;
import java.util.Optional;

import br.com.cadastroFuncionarios.repositories.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cadastroFuncionarios.domain.Cargo;

@Service
public class CargoService {

	@Autowired
	private CargoRepository repo;
	
	public List<Cargo> findAll() {
		return repo.findAll();
	}
	
	public Cargo find(Integer id) throws RuntimeException {
		Optional<Cargo> cargo = repo.findById(id);
		return cargo.orElseThrow(() -> new RuntimeException("Objeto não encontrado:" + this.getClass().getName() + " ID: " + id));
	}
	
	public Cargo insert(Cargo obj) throws RuntimeException {
		obj.setId(null);
		return repo.save(obj);
	}

	public Cargo update(Cargo obj) {
		find(obj.getId());
		return repo.save(obj);
	}

	public void delete(Integer id) {
		find(id);
		repo.deleteById(id);
	}
}
