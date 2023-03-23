package tv.wouri.azure.security;

import tv.wouri.azure.models.PCompte;
import tv.wouri.azure.models.SMutualiste;
import tv.wouri.azure.repositories.PCompteRepository;
import tv.wouri.azure.services.SMutualisteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class LoginService implements UserDetailsService {
    @Autowired
    PCompteRepository pCompteRepository;
    @Autowired
    SMutualisteService mutualisteService;

    @Override
    public Login loadUserByUsername(String username) throws UsernameNotFoundException {
        PCompte user = pCompteRepository.findByLoginUser(username);
        if (user == null) {
            throw new EntityNotFoundException("Utilisateur non trouvé");
        }

        SMutualiste mutualiste = mutualisteService.getByMatricule(user.getPMatricule());
        if(mutualiste == null) user.setPActivated(false);
        else if (!mutualiste.getMUSTATUT().equals("00001")) user.setPActivated(false);

        return new Login(user);
    }

}
