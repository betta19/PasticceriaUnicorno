package it.dstech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.dstech.models.Cliente;
import it.dstech.repository.ClienteRepository;
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

	
	@GetMapping("/entra")
    public String entraCome() {
        return "add-user";
    }
	
	
	@PostMapping("/addCliente")
    public String addCliente( Cliente cliente, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-cliente";
        }
        
        clienteService.addCliente(cliente);
        model.addAttribute("clienti", clienteService.findAllClienti());
        return "indexCliente";
    }
	
	@GetMapping("/delete/{id}")
    public String deleteCliente(@PathVariable("id") long id, Model model) {
       clienteService.deleteCliente(id);
        model.addAttribute("users", clienteService.findAllClienti());
        return "indexCliente";
    }
	
	
}
