package it.dstech.controller;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import it.dstech.models.Cliente;
import it.dstech.models.Dolce;
import it.dstech.models.Ingrediente;
import it.dstech.models.Ordinazione;
import it.dstech.models.Ricetta;
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
	
	private Logger logger = LoggerFactory.getLogger(PasticceriaController.class);

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
		logger.info(String.format("L'accesso è stato tenteto da %s", nome));
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
		logger.warn(String.format("L'utente sta modificanfo i suoi dati, fai attenzione! %s", cliente.getNome()));
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
		
		return "add-ricetta";
	}
	
	@GetMapping ("/fineRicetta")
	public String fineRicetta () {
		return "opzioniAdmin";
	}
	
	
	@PostMapping("/addRicetta")
	public String addIngrediente(@RequestParam("command") String command, Ricetta ricetta, BindingResult result, Model model) {

		if (result.hasErrors()) {
			return "index";
		}
		
		ricetta.setDifficolta(Integer.parseInt(command));
		ricettaService.addRicetta(ricetta);
		model.addAttribute("listaIngrediente", ingredienteService.findAllIngrediente());
		model.addAttribute("messaggio", "Ricetta aggiunta");
		model.addAttribute("ricetta", ricetta);

		return "add-ingrediente-a-ricetta";
	}
	
	@GetMapping("/salvaIngredientiRicetta")
	public String addIngredientiRicetta(@RequestParam(value = "ingredienti" , required = false) long[] idIngredienti , Model model,  @RequestParam("idRicetta") String idRicetta) {
		long idR = Long.parseLong(idRicetta);
		Ricetta recipe= ricettaService.findById(idR);
//		recipe.setId(ricetta.getId());
		
		if(idIngredienti != null) {
		   
		    for (int i = 0; i < idIngredienti.length; i++) {
		    	Ingrediente ingrediente = ingredienteService.findById(idIngredienti[i]);
		    	logger.info(String.format("stiamo aggiungendo l'ingrediente %s alla ricetta %s", ingrediente.getNome(), recipe.getNome()));
		    	if(ingrediente != null) {
		    	ingrediente = new  Ingrediente();
		    	ingrediente.setId(idIngredienti[i]);
		    	recipe.getIngrediente().add(ingredienteService.aggiungiIngredienteARicetta(idIngredienti[i], recipe));
		    	ricettaService.costoRicetta(recipe);
				ricettaService.addRicetta(recipe); 
		        }
		    } 
	
		}
		model.addAttribute("listaIngrediente", ingredienteService.findAllIngrediente());
		model.addAttribute("ricetta", recipe);
		return "add-ingrediente-a-ricetta";
	}
	
	@GetMapping("/eliminaR/{id}")
	public String deleteRicetta(@PathVariable("id") long id, Model model) {
		ricettaService.rimuoviRicetta(id);
		model.addAttribute("messaggio", "Ricetta eliminata");
		return "opzioniAdmin";
	}

	@GetMapping("/aggiungiDolce")
	public String addDolce(Dolce dolce, Ricetta ricetta, Model model) {
		model.addAttribute("listaRicetta", ricettaService.findAllRicette());
		model.addAttribute("listaDolci", dolceService.findAllDolci());
		return "add-dolce";
	}
	
	@PostMapping("/addDolce")
	public String salvaDolce(Dolce dolce, @RequestParam("idRicetta") String idRicetta, BindingResult result, Model model) {
		
//		Ricetta recipe = ricettaService.findById(ricetta.getId());
		
		if (result.hasErrors()) {
			return "index";
		}
		
		dolceService.addDolce(dolce, Long.parseLong(idRicetta));
		model.addAttribute("messaggio", "Dolce aggiunto");


		return "opzioniAdmin";
	}
	
	@GetMapping("/eliminaD/{id}")
	public String deleteDolce(@PathVariable("id") long id, Model model) {
		dolceService.rimuoviDolce(id);
		model.addAttribute("messaggio", "Dolce eliminato");
		return "opzioniAdmin";
	}
	
	
	
	@GetMapping("/gestioneOrdiniCliente/{id}")
	public String gestisciOrdinazioneCliente(@PathVariable("id") long id, Ordinazione ordinazione, Model model) {
		model.addAttribute("listaOrdinazione", ordinazioneService.listaOrdiniCliente(id));
		model.addAttribute("listaDolce", dolceService.findAllDolci());
		model.addAttribute("cliente", clienteService.findById(id));
		
		return "add-ordinazione";
	}
	
	@PostMapping("/addOrdinazione")
	public String addOrdine(@RequestParam(value = "dolci" , required = false) long[] idDolce ,@RequestParam("idCliente") String idCliente,  Ordinazione ordinazione, Model model) {
		
		Cliente cliente = clienteService.findById(Long.parseLong(idCliente));
		
		
		if(idDolce != null) {
			
		    for (int i = 0; i < idDolce.length; i++) {
		    	Dolce dolce = dolceService.findById(idDolce[i]);
		    	logger.info(String.format("stiamo aggiungendo il dolce %s all'ordinazione del cliente %s", dolce.getNome(), cliente.getNome()));
		    	if(dolce != null) {
	    		dolce = new Dolce();
	    		dolce.setId(idDolce[i]);
	    		List<Dolce> listaDolci = new ArrayList<Dolce>();
	    		listaDolci.add(dolceService.aggiungiOrdinazioneADolce(idDolce[i], ordinazione));
	    		ordinazione.setDolce(listaDolci);
	    		ordinazione.setCliente(cliente);
	    		ordinazione.setCostoOrdinazione(ordinazioneService.costoOrdinazione(idDolce[i]));
	    		ordinazioneService.addOrdinazione(ordinazione); 
		    	clienteService.findById(Long.parseLong(idCliente)).getOrdinazioni().add(ordinazione);
				clienteService.addCliente(clienteService.findById(Long.parseLong(idCliente)));
		    	
		    	}	
		    }
		    	
		}
		
		model.addAttribute("listaOrdinazione", ordinazioneService.listaOrdiniCliente(Long.parseLong(idCliente)));
		model.addAttribute("listaDolce", dolceService.findAllDolci());
		model.addAttribute("cliente", clienteService.findById(Long.parseLong(idCliente)));
		return "add-ordinazione";
	
	}
	
	@GetMapping("/gestioneOrdiniAdmin")
	public String gestisciOrdinazioneAdmin(Model model) {
		model.addAttribute("listaCompletato", amministratoreService.listaOrdinazioniPassate());
		model.addAttribute("listaDaCompletare", amministratoreService.listaOrdinazioniNuove());

		return "gestione-ordini";
	}
	
	@GetMapping ("/chiudi/{id}")
	public String chiudiOrdine (@PathVariable("id") long id, Model model) {
		Ordinazione ordine = ordinazioneService.findById(id);
		ordine.setCompletato(true);
		ordine.setId(id);
		ordinazioneService.addOrdinazione(ordine);
		logger.info("stiamo chiudendo l'ordine del cliente %s che ha già effettutato il pagamento di %d Euro", ordine.getCliente().getNome(), ordine.getCostoOrdinazione());
		model.addAttribute("listaCompletato", amministratoreService.listaOrdinazioniPassate());
		model.addAttribute("listaDaCompletare", amministratoreService.listaOrdinazioniNuove());

		return "gestione-ordini";
	}
	}
