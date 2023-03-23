package tv.wouri.azure.repositories;

import tv.wouri.azure.models.TAideFinanciere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TAideFinanciereRepository extends JpaRepository<TAideFinanciere, Integer> {

    @Query(value = "select * from t_aidefinanciere where MUNUMERO = ?1", nativeQuery = true)
    List<TAideFinanciere> findByMutualiste (int personne);

    @Query(value = "select * from t_aidefinanciere where AFSTATUT = ?1 and MUNUMERO = ?2", nativeQuery = true)
    List<TAideFinanciere> findByStatut (String statut,int personne);

    @Query(value = "select * from t_aidefinanciere where AFDATEPAI like %?1% and MUNUMERO = ?2", nativeQuery = true)
    List<TAideFinanciere> findByDate (String ask, int personne);

    @Query(value = "select * from t_aidefinanciere where AFDATEPAI like %?1% and AFSTATUT = ?2 and MUNUMERO = ?3", nativeQuery = true)
    List<TAideFinanciere> findByDateStatut (String date, String statut, int personne);



}
