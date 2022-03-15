package com.andre.os.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andre.os.domain.Cliente;
import com.andre.os.domain.OrdemServico;
import com.andre.os.domain.Tecnico;
import com.andre.os.domain.enums.Prioridade;
import com.andre.os.domain.enums.Status;
import com.andre.os.dtos.OrdemServicoDTO;
import com.andre.os.repositories.OrdemServicoRepository;
import com.andre.os.services.exceptions.ObjectNotFoundException;

@Service
public class OrdemServicoService {

	@Autowired
	private OrdemServicoRepository repository;
	
	@Autowired
	private TecnicoService tecnicoService;
	
	@Autowired
	private ClienteService clienteService;
	
	public OrdemServico findById(Integer id) {
		Optional<OrdemServico> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! ID: " + id + ", Tipo: " + OrdemServico.class.getName()));
	}
	
	public List<OrdemServico> findAll() {
		return repository.findAll();
	}

	public OrdemServico create(@Valid OrdemServicoDTO obj) {
		return fromDTO(obj);
	}
	
	public OrdemServico update(@Valid OrdemServicoDTO obj) {
		findById(obj.getId());
		return fromDTO(obj);
	}
	
	private OrdemServico fromDTO(OrdemServicoDTO obj) {
		OrdemServico newObj = new OrdemServico();
		newObj.setId(obj.getId());
		newObj.setObservacoes(obj.getObservacoes());
		newObj.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
		newObj.setStatus(Status.toEnum(obj.getStatus()));
		
		Tecnico tecnico = tecnicoService.findById(obj.getTecnico());
		Cliente cliente = clienteService.findById(obj.getCliente());
		
		newObj.setTecnico(tecnico);
		newObj.setCliente(cliente);
		
		if(newObj.getStatus().getCodigoStatus().equals(2)) {
			newObj.setDataFechamento(LocalDateTime.now());
		}
		
		return repository.save(newObj);
	}
}
