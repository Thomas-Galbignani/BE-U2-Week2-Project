package thomasgalbignani.BE_U2_Week2_Project.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import thomasgalbignani.BE_U2_Week2_Project.entities.Dipendente;
import thomasgalbignani.BE_U2_Week2_Project.exceptions.BadRequestException;
import thomasgalbignani.BE_U2_Week2_Project.exceptions.NotFoundException;
import thomasgalbignani.BE_U2_Week2_Project.payloads.DipendenteDTO;
import thomasgalbignani.BE_U2_Week2_Project.repositories.DipendenteRepository;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
public class DipendenteService {

    @Autowired
    private DipendenteRepository dipendenteRepository;
    @Autowired
    private Cloudinary imageUpload;

    private void isUniquene(String username, String email) {
        if (dipendenteRepository.existsByUsername(username)) {
            throw new BadRequestException("L'username " + username + " è già in uso!");
        }
        if (dipendenteRepository.existsByEmail(email)) {
            throw new BadRequestException("L'email " + email + " è già in uso!");
        }
    }

    public Dipendente save(DipendenteDTO newDipendenteDTO) {
        isUniquene(newDipendenteDTO.getUsername(), newDipendenteDTO.getEmail());

        Dipendente newDipendente = new Dipendente();
        newDipendente.setUsername(newDipendenteDTO.getUsername());
        newDipendente.setNome(newDipendenteDTO.getNome());
        newDipendente.setCognome(newDipendenteDTO.getCognome());
        newDipendente.setEmail(newDipendenteDTO.getEmail());


        return dipendenteRepository.save(newDipendente);
    }

    public Page<Dipendente> getDipendenti(int page, int size, String sortBy) {
        if (size > 100) size = 100; // Limite di sicurezza
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return dipendenteRepository.findAll(pageable);
    }


    public Dipendente findById(UUID id) {
        return dipendenteRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }


    public Dipendente findByIdAndUpdate(UUID id, DipendenteDTO updatedDipendenteDTO) {
        Dipendente found = findById(id);

        if (!found.getUsername().equals(updatedDipendenteDTO.getUsername()) && dipendenteRepository.existsByUsername(updatedDipendenteDTO.getUsername())) {
            throw new BadRequestException("L'username " + updatedDipendenteDTO.getUsername() + " è già in uso!");
        }

        if (!found.getEmail().equals(updatedDipendenteDTO.getEmail()) && dipendenteRepository.existsByEmail(updatedDipendenteDTO.getEmail())) {
            throw new BadRequestException("L'email " + updatedDipendenteDTO.getEmail() + " è già in uso!");
        }

        found.setUsername(updatedDipendenteDTO.getUsername());
        found.setNome(updatedDipendenteDTO.getNome());
        found.setCognome(updatedDipendenteDTO.getCognome());
        found.setEmail(updatedDipendenteDTO.getEmail());

        return dipendenteRepository.save(found);
    }


    public void findByIdAndDelete(UUID id) {
        Dipendente found = findById(id);
        dipendenteRepository.delete(found);
    }


    public Dipendente uploadDipendenteAvatar(UUID dipendenteId, MultipartFile file) {
        Dipendente found = findById(dipendenteId);
        if (file.isEmpty()) throw new BadRequestException("non è presente il file immagine");

        try {
            Map result = imageUpload.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            String imageUrl = (String) result.get("url");
            found.setAvatar(imageUrl);
            dipendenteRepository.save(found);
            return found;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}