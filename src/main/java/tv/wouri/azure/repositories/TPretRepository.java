package tv.wouri.azure.repositories;

import tv.wouri.azure.models.TPret;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TPretRepository extends JpaRepository<TPret, Integer> {
    @Query(value = "select * from t_pret where MUNUMERO = ?1", nativeQuery = true)
    List<TPret> findByMutualiste (int personne);

    @Query(value = "select * from t_pret where PRSTATUT = ?1 and MUNUMERO = ?2", nativeQuery = true)
    List<TPret> findByStatut (String statut, int personne);

    @Query(value = "select * from t_pret where PRDATEDEMANDE like %?1% and MUNUMERO = ?2", nativeQuery = true)
    List<TPret> findByDate (String ask,int personne);

    @Query(value = "select * from t_pret where PRDATEDEMANDE like %?1% and PRSTATUT = ?2 and MUNUMERO = ?3", nativeQuery = true)
    List<TPret> findByDateStatut (String date, String statut,int personne);
}

