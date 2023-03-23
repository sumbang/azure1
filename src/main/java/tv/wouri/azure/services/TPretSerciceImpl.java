package tv.wouri.azure.services;

import tv.wouri.azure.models.TPret;
import tv.wouri.azure.repositories.TPretRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TPretSerciceImpl implements TPretService {
    @Autowired
    TPretRepository tPretRepository;

    @Override
    public TPret save(TPret m) {
        return tPretRepository.save(m);
    }
    @Override
    public TPret update(TPret m) {
        return tPretRepository.save(m);
    }
    @Override
    public void delete(TPret m) {
        tPretRepository.delete(m);
    }
    @Override
    public TPret get(Integer id) {
        return tPretRepository.findById(id).get();
    }
    @Override
    public List<TPret> getByMutualiste(int personne) {
        return tPretRepository.findByMutualiste(personne);
    }

    @Override
    public List<TPret> getAll() {
        return tPretRepository.findAll();
    }

    @Override
    public List<TPret> getByDateStatut(String date, String statut,int personne) {
        if(statut.isEmpty() && !date.isEmpty()) return tPretRepository.findByDate(date,personne);
        else if(date.isEmpty() && !statut.isEmpty()) return tPretRepository.findByStatut(statut,personne);
        else if(date.isEmpty() && statut.isEmpty()) return tPretRepository.findByMutualiste(personne);
        else return tPretRepository.findByDateStatut(date,statut,personne);
    }
}

