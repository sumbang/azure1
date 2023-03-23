package tv.wouri.azure.services;

import tv.wouri.azure.models.PCompte;
import tv.wouri.azure.repositories.PCompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class PCompteService extends AbstractService<PCompte, Long> {

   @Autowired
    private PCompteRepository pCompteRepository;

    @Override
    protected JpaRepository<PCompte, Long> getRepository() {
        return pCompteRepository;
    }

    public PCompte findByLoginUser(String login) {
        return pCompteRepository.findByLoginUser(login);
    }

    public PCompte findByResetToken(String token){
        return pCompteRepository.findByResetToken(token);
    }

    public PCompte findByMatricule(String matricule) {
        return pCompteRepository.findByMatricule(matricule);
    }

    public PCompte findByActivatedToken(String token){
        return pCompteRepository.findByActivedToken(token);
    }
}
