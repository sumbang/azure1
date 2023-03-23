package tv.wouri.azure.services;

import tv.wouri.azure.models.TAideFinanciere;
import tv.wouri.azure.repositories.TAideFinanciereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TAideFinanciereServiceImpl implements TAideFinanciereService {

    @Autowired
    TAideFinanciereRepository tAideFinanciereRepository;

    @Override
    public TAideFinanciere save(TAideFinanciere m) {
        return tAideFinanciereRepository.save(m);
    }
    @Override
    public TAideFinanciere update(TAideFinanciere m) {
        return tAideFinanciereRepository.save(m);
    }
    @Override
    public void delete(TAideFinanciere m) {
        tAideFinanciereRepository.delete(m);
    }
    @Override
    public TAideFinanciere get(Integer id) {
        return tAideFinanciereRepository.findById(id).get();
    }
    @Override
    public List<TAideFinanciere> getByMutualiste(int personne) {
        return tAideFinanciereRepository.findByMutualiste(personne);
    }

    @Override
    public List<TAideFinanciere> getAll() {
        return tAideFinanciereRepository.findAll();
    }

    @Override
    public List<TAideFinanciere> getByDateStatut(String date, String statut, int personne) {
        if(statut.isEmpty() && !date.isEmpty()) return tAideFinanciereRepository.findByDate(date, personne);
        else if(date.isEmpty() && !statut.isEmpty()) return tAideFinanciereRepository.findByStatut(statut, personne);
        else if(date.isEmpty() && statut.isEmpty()) return tAideFinanciereRepository.findByMutualiste(personne);
        else return tAideFinanciereRepository.findByDateStatut(date,statut, personne);
    }
}

