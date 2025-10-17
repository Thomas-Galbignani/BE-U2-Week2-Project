package thomasgalbignani.BE_U2_Week2_Project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import thomasgalbignani.BE_U2_Week2_Project.entities.Viaggio;
import thomasgalbignani.BE_U2_Week2_Project.payloads.ViaggioDTO;
import thomasgalbignani.BE_U2_Week2_Project.services.ViaggioService;

import java.util.UUID;

@RestController
@RequestMapping("/viaggi")
public class ViaggioController {

    @Autowired
    private ViaggioService viaggioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // HTTP 201
    public Viaggio createViaggio(@RequestBody @Validated ViaggioDTO body) {
        return viaggioService.create(body);
    }

    @GetMapping
    public Page<Viaggio> findAll(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "10") int size,
                                 @RequestParam(defaultValue = "dataViaggio") String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return viaggioService.findAll(pageable);
    }

    @GetMapping("/{viaggioId}")
    public Viaggio findById(@PathVariable UUID viaggioId) {
        return viaggioService.findById(viaggioId);
    }

    @PutMapping("/{viaggioId}")
    public Viaggio findByIdAndUpdate(@PathVariable UUID viaggioId, @RequestBody @Validated ViaggioDTO body) {
        return viaggioService.findByIdAndUpdate(viaggioId, body);
    }


    @DeleteMapping("/{viaggioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // HTTP 204
    public void findByIdAndDelete(@PathVariable UUID viaggioId) {
        viaggioService.findByIdAndDelete(viaggioId);
    }
}