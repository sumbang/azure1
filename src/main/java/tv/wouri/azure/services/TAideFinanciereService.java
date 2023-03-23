package tv.wouri.azure.services;

import tv.wouri.azure.models.TAideFinanciere;

import java.util.List;

public interface TAideFinanciereService {
    TAideFinanciere save(TAideFinanciere m);
    TAideFinanciere update(TAideFinanciere m);
    void delete(TAideFinanciere m);
    TAideFinanciere get(Integer id);
    List<TAideFinanciere> getByMutualiste(int personne);
    List<TAideFinanciere> getByDateStatut(String date, String statut, int personne);
    List<TAideFinanciere> getAll();
}
