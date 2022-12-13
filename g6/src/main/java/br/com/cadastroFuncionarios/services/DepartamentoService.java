package br.com.cadastroFuncionarios.services;

import java.util.List;
import java.util.Optional;

import br.com.cadastroFuncionarios.repositories.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cadastroFuncionarios.domain.Departamento;

@Service
public class DepartamentoService {

	@Autowired
	private DepartamentoRepository repo;
	
	public List<Departamento> findAll() {
		return repo.findAll();
	}
	
	public Departamento find(Integer id) throws RuntimeException {
		Optional<Departamento> obj = repo.findById(id);
		return obj.orElseThrow(() -> new RuntimeException("Objeto não encontrado:" + this.getClass().getName() + " ID: " + id));
	}
	
	public Departamento insert(Departamento obj) throws RuntimeException {
		obj.setId(null);
		return repo.save(obj);
	}

	public Departamento update(Departamento obj) {
		find(obj.getId());
		return repo.save(obj);
	}

	public void delete(Integer id) {
		find(id);
		repo.deleteById(id);
	}
}
