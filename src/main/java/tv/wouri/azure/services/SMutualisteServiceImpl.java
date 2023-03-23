package tv.wouri.azure.services;

import tv.wouri.azure.models.SMutualiste;
import tv.wouri.azure.repositories.SMutualisteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SMutualisteServiceImpl  implements SMutualisteService {

    @Autowired
    SMutualisteRepository sMutualisteRepository;

    @Override
    public SMutualiste save(SMutualiste m) {
        return sMutualisteRepository.save(m);
    }

    @Override
    public SMutualiste update(SMutualiste m) {
        return sMutualisteRepository.save(m);
    }

    @Override
    public void delete(SMutualiste m) {
         sMutualisteRepository.delete(m);
    }

    @Override
    public SMutualiste get(Integer id) {
        return sMutualisteRepository.findById(id).get();
    }

    @Override
    public SMutualiste getByMatricule(String matricule) {
        return sMutualisteRepository.findByMatricule(matricule);
    }

    @Override
    public List<SMutualiste> getAll() {
        return sMutualisteRepository.findAll();
    }

}
