package br.com.cadastroFuncionarios;

import java.sql.Date;
import java.util.Arrays;
import java.util.Calendar;

import br.com.cadastroFuncionarios.repositories.CargoRepository;
import br.com.cadastroFuncionarios.repositories.DepartamentoRepository;
import br.com.cadastroFuncionarios.repositories.FuncionarioDepartamentoRepository;
import br.com.cadastroFuncionarios.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.cadastroFuncionarios.domain.Cargo;
import br.com.cadastroFuncionarios.domain.Departamento;
import br.com.cadastroFuncionarios.domain.Funcionario;
import br.com.cadastroFuncionarios.domain.FuncionarioDepartamento;
import br.com.cadastroFuncionarios.domain.FuncionarioDepartamentoPK;

@SpringBootApplication
public class CadastroFuncionariosApplication implements CommandLineRunner{
	
	@Autowired
	private CargoRepository cargoRepository;
	
	@Autowired
	private DepartamentoRepository deptoRepository;
	
	@Autowired
	private FuncionarioRepository funcRepository;
	
	@Autowired
	private FuncionarioDepartamentoRepository funcDeptoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CadastroFuncionariosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Cargo cargo1 = new Cargo(1,"Gerente");
		Cargo cargo2 = new Cargo(2,"Auxiliar");
		Cargo cargo3 = new Cargo(3,"Estagiario");

		cargoRepository.saveAll(Arrays.asList(cargo1,cargo2,cargo3));
		
		Departamento depto1 = new Departamento(null, "Financeiro");
		Departamento depto2 = new Departamento(null, "TI");
		Departamento depto3 = new Departamento(null, "Juridico");
		Departamento depto4 = new Departamento(null, "Contabilidade");
		
		deptoRepository.saveAll(Arrays.asList(depto1,depto2,depto3,depto4));
		
		Funcionario func1 = new Funcionario();
		func1.setId(null);
		func1.setName("TESTE");
		func1.setAge(22);
		func1.setBirthday(new Date(Calendar.getInstance().getTimeInMillis()));
		func1.setDocument("11111111");
		func1.setCargo(cargo1);
		
		Funcionario func2 = new Funcionario();
		func2.setId(null);
		func2.setName("TESTE2");
		func2.setAge(41);
		func2.setBirthday(new Date(Calendar.getInstance().getTimeInMillis()));
		func2.setDocument("22222222");
		func2.setCargo(cargo2);
		
		Funcionario func3 = new Funcionario();
		func3.setId(null);
		func3.setName("TESTE3");
		func3.setAge(41);
		func3.setBirthday(new Date(Calendar.getInstance().getTimeInMillis()));
		func3.setDocument("33333333");
		func3.setCargo(cargo3);
		
		funcRepository.saveAll(Arrays.asList(func1,func2,func3));
		
		FuncionarioDepartamentoPK funcdeptopk1 = new FuncionarioDepartamentoPK();
		funcdeptopk1.setFuncionario(func1);
		funcdeptopk1.setDepartamento(depto1);
		funcdeptopk1.setStartDate(new Date(Calendar.getInstance().getTimeInMillis()));
		
		FuncionarioDepartamento funcdepto1 = new FuncionarioDepartamento();
		funcdepto1.setId(funcdeptopk1);
		
		Thread.sleep(1000);
		
		FuncionarioDepartamentoPK funcdeptopk2 = new FuncionarioDepartamentoPK();
		funcdeptopk2.setFuncionario(func1);
		funcdeptopk2.setDepartamento(depto2);
		funcdeptopk2.setStartDate(new Date(Calendar.getInstance().getTimeInMillis()));
		
		FuncionarioDepartamento funcdepto2 = new FuncionarioDepartamento();
		funcdepto2.setId(funcdeptopk2);
		
		FuncionarioDepartamentoPK funcdeptopk3 = new FuncionarioDepartamentoPK();
		funcdeptopk3.setFuncionario(func3);
		funcdeptopk3.setDepartamento(depto1);
		funcdeptopk3.setStartDate(new Date(Calendar.getInstance().getTimeInMillis()));
		
		FuncionarioDepartamento funcdepto3 = new FuncionarioDepartamento();
		funcdepto3.setId(funcdeptopk3);
		
		funcDeptoRepository.saveAll(Arrays.asList(funcdepto1,funcdepto2,funcdepto3));
		
	}

}
