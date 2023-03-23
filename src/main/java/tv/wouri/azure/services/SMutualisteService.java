package tv.wouri.azure.services;

import tv.wouri.azure.models.SMutualiste;

import java.util.List;

public interface SMutualisteService  {

    SMutualiste save(SMutualiste m);
    SMutualiste update(SMutualiste m);
    void delete(SMutualiste m);
    SMutualiste get(Integer id);
    SMutualiste getByMatricule(String matricule);
    List<SMutualiste> getAll();

}
