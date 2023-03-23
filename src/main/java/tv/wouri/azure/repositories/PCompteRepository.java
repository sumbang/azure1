package tv.wouri.azure.repositories;

import tv.wouri.azure.models.PCompte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface PCompteRepository  extends JpaRepository<PCompte, Long> {

    @Query(value = "select * from web_compte where pmatricule = ?1", nativeQuery = true)
    PCompte findByLoginUser (String username);

    @Query(value = "select * from web_compte where presettoken = ?1", nativeQuery = true)
    PCompte findByResetToken (String token);

    @Query(value = "select * from web_compte where pactivedtoken = ?1", nativeQuery = true)
    PCompte findByActivedToken (String token);

    @Query(value = "select * from web_compte where pmatricule = ?1", nativeQuery = true)
    PCompte findByMatricule (String matricule);
}
