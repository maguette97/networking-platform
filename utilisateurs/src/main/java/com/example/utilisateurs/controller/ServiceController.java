package com.example.utilisateurs.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.utilisateurs.model.Service;
import com.example.utilisateurs.model.Utilisateur;
import com.example.utilisateurs.repository.ServiceRepository;
import com.example.utilisateurs.repository.UtilisateurRepository;

@RestController
@RequestMapping("/api/services")
@CrossOrigin(origins = "http://localhost:3000")
public class ServiceController {
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private UtilisateurRepository utilisateurRepository;


    // Endpoint pour la publication de services

        @PostMapping("/publier")
    public ResponseEntity<?> publierService(@RequestBody Service service) {
        // Enregistrer le service dans la base de données
        Service nouveauService = serviceRepository.save(service);
        return ResponseEntity.ok("Service publié avec succès !");
    }
    /*    @PostMapping("/publier")
    public ResponseEntity<?> publierService(@RequestParam("image") MultipartFile image, @RequestBody Service service) {
        try {
            // Vérifiez si une image a été téléchargée
            if (!image.isEmpty()) {
                // Récupérez le répertoire de stockage des images à partir de la configuration
                String uploadDir = "src/main/resources/images"; // Remplacez par le chemin approprié

                // Assurez-vous que le répertoire de stockage des images existe, sinon créez-le
                Files.createDirectories(Path.of(uploadDir));

                // Générez un nom de fichier unique pour éviter les collisions
                String fileName = StringUtils.cleanPath(image.getOriginalFilename());
                String uniqueFileName = System.currentTimeMillis() + "_" + fileName;

                // Copiez le fichier téléchargé vers le répertoire de stockage des images
                Path filePath = Path.of(uploadDir, uniqueFileName);
                Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                // Enregistrez le chemin d'accès de l'image dans l'objet Service
                service.setPhoto("/src/main/resources/images/" + uniqueFileName);
            }

            // Enregistrez le service dans la base de données
            Service nouveauService = serviceRepository.save(service);

            return ResponseEntity.ok("Service publié avec succès !");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body("Erreur lors de l'enregistrement de l'image.");
        }
    }
 */   
    
 // Endpoint pour la liste des services en attente
    @GetMapping("/pending")
    public List<Service> getServicesEnAttente() {
        // Récupérer et retourner la liste des services en attente
        List<Service> servicesEnAttente = serviceRepository.findByValideFalse();
        return servicesEnAttente;
    }


    // Endpoint pour la liste des services validés
    @GetMapping("/valides")
    public List<Service> getServicesValides() {
        // Récupérer et retourner la liste des services validés
        List<Service> servicesValides = serviceRepository.findByValideTrue();
        return servicesValides;
    }
    
    @PutMapping("/{serviceId}/valider")
    public ResponseEntity<?> validerService(@PathVariable Long serviceId) {
        // Recherche du service par ID et mise à jour du statut de validation
        Optional<Service> optionalService = serviceRepository.findById(serviceId);
        if (optionalService.isPresent()) {
            Service service = optionalService.get();
            service.setValide(true);
            serviceRepository.save(service);
            return ResponseEntity.ok("Service validé avec succès !");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    
}
