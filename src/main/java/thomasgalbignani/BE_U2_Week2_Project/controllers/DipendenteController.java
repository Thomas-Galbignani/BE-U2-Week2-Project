package thomasgalbignani.BE_U2_Week2_Project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import thomasgalbignani.BE_U2_Week2_Project.entities.Dipendente;
import thomasgalbignani.BE_U2_Week2_Project.payloads.DipendenteDTO;
import thomasgalbignani.BE_U2_Week2_Project.services.DipendenteService;

import java.util.UUID;

@RestController
@RequestMapping("/dipendenti")
public class DipendenteController {

    @Autowired
    private DipendenteService dipendenteService;

    //creazione dipendente
    //post http://localhost:3001/dipendenti

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // Status 201
    public Dipendente createDipendente(@RequestBody @Validated DipendenteDTO body) {
        return dipendenteService.save(body);
    }

    // get di tutti i dipendenti
    // get http://localhost:3001/dipendenti

    @GetMapping
    public Page<Dipendente> getDipendenti(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        return dipendenteService.getDipendenti(page, size, sortBy);
    }

    //get su uno specifico dipendente con uno specifico id passato come variabile
    // http://localhost:3001/dipendenti/{dipendentiId}

    @GetMapping("/{dipendenteId}")
    public Dipendente findById(@PathVariable UUID dipendenteId) {
        return dipendenteService.findById(dipendenteId);
    }


    // http://localhost:3001/dipendenti/{dipendentiId}
    // fá la put su un dipendente specifico in base all'id
    @PutMapping("/{dipendenteId}")
    public Dipendente findByIdAndUpdate(@PathVariable UUID dipendenteId, @RequestBody @Validated DipendenteDTO body) {
        return dipendenteService.findByIdAndUpdate(dipendenteId, body);
    }

    // http://localhost:3001/dipendenti/{dipendentiId}
    // fá la delete su un dipendente specifico in base all'id
    @DeleteMapping("/{dipendenteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // Status 204
    public void findByIdAndDelete(@PathVariable UUID dipendenteId) {
        dipendenteService.findByIdAndDelete(dipendenteId);
    }


    @PatchMapping("/{authorId}/avatar")
    public Dipendente updateAvatarAuthor(@PathVariable UUID dipendenteId, @RequestParam("avatar") MultipartFile file) {
        return dipendenteService.uploadDipendenteAvatar(dipendenteId, file);
    }

}