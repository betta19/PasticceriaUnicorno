package it.dstech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import it.dstech.service.AmministratoreServiceDAO;
import it.dstech.service.ClienteServiceDAO;
import it.dstech.service.DolceServiceDAO;
import it.dstech.service.IngredienteServiceDAO;
import it.dstech.service.OrdinazioneServiceDAO;
import it.dstech.service.RicettaServiceDAO;


@Controller
public class PasticceriaController {
	
	@Autowired
	private ClienteServiceDAO clienteService;
	
	@Autowired
	private AmministratoreServiceDAO amministratoreService;
	
	@Autowired
	private DolceServiceDAO dolceService;
	
	@Autowired
	private IngredienteServiceDAO ingredientiService;
	
	@Autowired
	private OrdinazioneServiceDAO ordinazioneService;
	
	@Autowired
	private RicettaServiceDAO ricettaService;

}
