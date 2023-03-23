package tv.wouri.azure.security;

import tv.wouri.azure.models.*;
import tv.wouri.azure.repositories.TTransactionmutualisteRepository;
import tv.wouri.azure.services.PCompteService;
import tv.wouri.azure.services.SMutualisteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class SecurityController {

    @Autowired
    PCompteService pCompteService;
    @Autowired
    TTransactionmutualisteRepository tTransactionmutualisteRepository;
    @Autowired
    SMutualisteService sMutualisteService;

    @GetMapping("/accessDenied")
    public String error()
    {
        return "accessDenied";
    }

    @GetMapping("/login")
    public String login()
    {
        return "login";
    }

    @GetMapping("/")
    public String index(Model model){
        
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        PCompte user = pCompteService.findByLoginUser(userDetails.getUsername());
        SMutualiste mutualiste = sMutualisteService.getByMatricule(user.getPMatricule());

        List<TTransactionmutualiste> transactionmutualisteList =  tTransactionmutualisteRepository.findMutualisteTransaction(mutualiste.getMUNUMERO(),10);

        List<TPretMutualiste> tEncoursPretMutualistes = tTransactionmutualisteRepository.findEncoursPretMutualiste(mutualiste.getMUNUMERO(),10);

        List<TPretMutualisePending> tValidationPretMutualiste = tTransactionmutualisteRepository.findValidationPretMutualiste(mutualiste.getMUNUMERO(),10);

        List<TPretMutualisePending> tEncaissementPretMutualiste = tTransactionmutualisteRepository.findEncaissementPretMutualiste(mutualiste.getMUNUMERO(), 10);

        TCotisation tCotisation = tTransactionmutualisteRepository.findCumulCotisationMutualiste(mutualiste.getMUNUMERO());

        model.addAttribute("mutualiste", mutualiste);
        model.addAttribute("transactions", transactionmutualisteList);
        model.addAttribute("encours", tEncoursPretMutualistes);
        model.addAttribute("validations", tValidationPretMutualiste);
        model.addAttribute("encaissements", tEncaissementPretMutualiste);
        model.addAttribute("cotisation", tCotisation);
        model.addAttribute("user", mutualiste.fullname());

        return "dashboard/index";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) throws ServletException
    {
        request.logout();
        return "redirect:/login";
    }
}
