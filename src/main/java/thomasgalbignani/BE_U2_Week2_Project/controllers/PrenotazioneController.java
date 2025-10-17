package thomasgalbignani.BE_U2_Week2_Project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import thomasgalbignani.BE_U2_Week2_Project.entities.Prenotazione;
import thomasgalbignani.BE_U2_Week2_Project.payloads.PrenotazioneDTO;
import thomasgalbignani.BE_U2_Week2_Project.services.PrenotazioneService;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {

    @Autowired
    private PrenotazioneService prenotazioneService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // HTTP 201
    public Prenotazione createPrenotazione(@RequestBody @Validated PrenotazioneDTO body) {
        return prenotazioneService.create(body);
    }

    @GetMapping // Mappato su /prenotazioni
    public Page<Prenotazione> findAll(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "10") int size,
                                      @RequestParam(defaultValue = "dataRichiesta") String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return prenotazioneService.findAll(pageable);
    }
}