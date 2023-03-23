package tv.wouri.azure.repositories;

import tv.wouri.azure.models.SMutualiste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SMutualisteRepository  extends JpaRepository<SMutualiste, Integer> {

    @Query(value = "select * from s_mutualiste where MUMATRICULE = ?1", nativeQuery = true)
    SMutualiste findByMatricule (String matricule);

}
