package com.example.tableau.repos;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.tableau.entities.Artiste;
@RepositoryRestResource(path = "art")
@CrossOrigin(origins = "http://localhost:4200")  // Allow Angular app to call these endpoints

public interface ArtisteRepository extends JpaRepository<Artiste, Long> {
}