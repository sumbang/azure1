package tv.wouri.azure.services;

import tv.wouri.azure.models.TPret;

import java.util.List;

public interface TPretService {

    TPret save(TPret m);
    TPret update(TPret m);
    void delete(TPret m);
    TPret get(Integer id);
    List<TPret> getByMutualiste(int personne);
    List<TPret> getAll();
    List<TPret> getByDateStatut(String date, String statut, int personne);
}
