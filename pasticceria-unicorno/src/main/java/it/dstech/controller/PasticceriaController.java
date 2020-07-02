package it.dstech.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import it.dstech.models.Cliente;
import it.dstech.models.Ingrediente;
import it.dstech.models.Ricetta;
import it.dstech.repository.ClienteRepository;
import it.dstech.repository.RicettaRepository;
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
	private IngredienteServiceDAO ingredienteService;

	@Autowired
	private OrdinazioneServiceDAO ordinazioneService;

	@Autowired
	private RicettaServiceDAO ricettaService;

	@GetMapping("/entra")
	public String entra(Model model) {
		model.addAttribute("cliente", clienteService.findAllClienti());
		return "index";
	}

	@GetMapping("/amministratore")
	public String paginaAdmin() {

		return "indexAdmin";
	}

	@PostMapping("/accessoAdmin")
	public String opzioniAdmin(@RequestParam("nome") String nome, Model model) {
		if ("admin".equalsIgnoreCase(nome)) {
			return "opzioniAdmin";
		}
		model.addAttribute("messaggio", "Non sei l'admin, prova ad entrare come cliente");
		return "index";
	}

	@GetMapping("/addCliente")
	public String addCliente(Cliente cliente) {

		return "add-cliente";
	}

	@PostMapping("/aggiungiCliente")
	public String salvaCliente(Cliente cliente, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "index";
		}

		clienteService.addCliente(cliente);
		model.addAttribute("cliente", clienteService.findAllClienti());
		return "index";
	}

	@GetMapping("/modificaC/{id}")
	public String modificaCliente(@PathVariable("id") long id, Model model) {
		Cliente cliente = clienteService.findById(id);
		model.addAttribute("cliente", cliente);
		return "modifica-cliente";
	}

	@PostMapping("/modificaCliente/{id}")
	public String salvaModificheCliente(@PathVariable("id") long id, Cliente cliente, BindingResult result,
			Model model) {
		model.addAttribute("cliente", clienteService.findAllClienti());
		if (result.hasErrors()) {
			return "index";
		}
		cliente.setId(id);
		clienteService.save(cliente);
		
		return "index";
	}

	@GetMapping("/eliminaC/{id}")
	public String deleteCliente(@PathVariable("id") long id, Model model) {
		clienteService.deleteCliente(id);
		model.addAttribute("cliente", clienteService.findAllClienti());
		return "index";
	}

	@GetMapping("/aggiungiIngrediente")
	public String aggiungiIngrediente(Ingrediente ingrediente, Model model) {
		model.addAttribute("listaIngrediente", ingredienteService.findAllIngrediente());
		return "add-ingrediente";
	}

	@PostMapping("/addIngrediente")
	public String addIngrediente(Ingrediente ingrediente, BindingResult result, Model model) {

		if (result.hasErrors()) {
			return "index";
		}

		ingredienteService.addIngrediente(ingrediente);
		model.addAttribute("messaggio", "Ingrediente aggiunto");

		return "opzioniAdmin";
	}
	

	@GetMapping("/modificaI/{id}")
	public String modificaIngrediente(@PathVariable("id") long id, Model model) {
		Ingrediente ingrediente = ingredienteService.findById(id);
		model.addAttribute("ingrediente", ingrediente);
		return "modifica-ingrediente";
	}

	@PostMapping("/modificaIngrediente/{id}")
	public String salvaModificheIngrediente(@PathVariable("id") long id, Ingrediente ingrediente, BindingResult result,
			Model model) {
		model.addAttribute("listaIngrediente", ingredienteService.findAllIngrediente());
		if (result.hasErrors()) {
			return "index";
		}
		ingrediente.setId(id);
		ingrediente.setDisponibile(true);
		ingredienteService.save(ingrediente);
		model.addAttribute("ingrediente", new Ingrediente());
		return "add-ingrediente";
	}

	@GetMapping("/eliminaI/{id}")
	public String deleteIngrediente(@PathVariable("id") long id, Model model) {
		ingredienteService.rimuoviIngrediente(id);
		model.addAttribute("messaggio", "Ingrediente eliminato");
		return "opzioniAdmin";
	}

	@GetMapping("/aggiungiRicetta")
	public String addRicetta(Model model, Ricetta ricetta) {
		model.addAttribute("listaRicetta", ricettaService.findAllRicette());
		model.addAttribute("listaIngrediente", ingredienteService.findAllIngrediente());
		return "add-ricetta";
	}
	
	@PostMapping("/addRicetta")
	public String addIngrediente(@RequestParam("command") String command, Ricetta ricetta, BindingResult result, Model model) {

		if (result.hasErrors()) {
			return "index";
		}
		
		ricetta.setDifficolta(Integer.parseInt(command));
		ricettaService.addRicetta(ricetta);
		model.addAttribute("messaggio", "Ricetta aggiunta");

		return "opzioniAdmin";
	}
	
	@GetMapping("/salvaIngredientiRicetta/{idIngrediente}")
	public String addIngredientiRicetta(@PathVariable("idIngrediente") long id, Model model, Ricetta ricetta) {
		Ricetta recipe= ricettaService.findById(ricetta.getId());
		ingredienteService.aggiungiIngredienteARicetta(id, recipe);
		ricettaService.addRicetta(recipe);
		model.addAttribute("listaRicetta", ricettaService.findAllRicette());
		model.addAttribute("listaIngrediente", ingredienteService.findAllIngrediente());
		model.addAttribute("ricetta", ricetta);
		return "add-ricetta";
	}
	
	@GetMapping("/eliminaR/{id}")
	public String deleteRicetta(@PathVariable("id") long id, Model model) {
		ricettaService.rimuoviRicetta(id);
		model.addAttribute("messaggio", "Ricetta eliminata");
		return "opzioniAdmin";
	}

	@GetMapping("/aggiungiDolce")
	public String addDolce() {

		return "add-dolce";
	}

}
