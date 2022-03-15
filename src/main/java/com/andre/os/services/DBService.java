package com.andre.os.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andre.os.domain.Cliente;
import com.andre.os.domain.OrdemServico;
import com.andre.os.domain.Tecnico;
import com.andre.os.domain.enums.Prioridade;
import com.andre.os.domain.enums.Status;
import com.andre.os.repositories.ClienteRepository;
import com.andre.os.repositories.OrdemServicoRepository;
import com.andre.os.repositories.TecnicoRepository;

@Service
public class DBService {
	
	@Autowired
	private TecnicoRepository tecnicoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private OrdemServicoRepository ordemServicoRepository;

	
	public void instanciaDB() {
		Tecnico t1 = new Tecnico(null, "Valdir Cezar", "994.721.360-95", "(41) 94444-4444");
		Cliente c1 = new Cliente(null, "Betina Campos", "640.329.010-07", "(41) 93333-3333");
		OrdemServico os1 = new OrdemServico(null, Prioridade.ALTA, "Trocar fonte do computador", Status.ANDAMENTO, t1, c1);
		
		t1.getList().add(os1);
		c1.getList().add(os1);
		
		tecnicoRepository.saveAll(Arrays.asList(t1));
		clienteRepository.saveAll(Arrays.asList(c1));
		ordemServicoRepository.saveAll(Arrays.asList(os1));
	}

}
