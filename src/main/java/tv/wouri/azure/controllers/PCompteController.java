package tv.wouri.azure.controllers;

import tv.wouri.azure.models.*;
import tv.wouri.azure.repositories.TEcheanceRemboursementRepository;
import tv.wouri.azure.repositories.TTransactionmutualisteRepository;
import tv.wouri.azure.services.PCompteService;
import tv.wouri.azure.services.SMutualisteService;
import tv.wouri.azure.services.TAideFinanciereService;
import tv.wouri.azure.services.TPretService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("comptes")
public class PCompteController {

    @Autowired
    private PCompteService pCompteService;
    @Autowired
    private TTransactionmutualisteRepository tTransactionmutualisteRepository;
    @Autowired
    private SMutualisteService sMutualisteService;

    @Autowired
    private TPretService tPretService;

    @Autowired
    private TAideFinanciereService tAideFinanciereService;
    @Autowired
    private TEcheanceRemboursementRepository tEcheanceRemboursementRepository;

    @GetMapping(value = "/historique/{pageNumber}")
    public String historique(@PathVariable Integer pageNumber, @RequestParam("debut") Optional<String> debut, @RequestParam("fin") Optional<String> fin, Model model) {

        int taille = 10;

        if(debut.isPresent() && fin.isPresent()) {

            String debut1 = debut.get();
            String fin1 = fin.get();

            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal();
            PCompte user = pCompteService.findByLoginUser(userDetails.getUsername());
            SMutualiste mutualiste = sMutualisteService.getByMatricule(user.getPMatricule());

            List<THistoriqueMutualiste> tHistoriqueMutualistes = tTransactionmutualisteRepository.findAllHistoriqueMvtMutualiste1(debut1,fin1,mutualiste.getMUNUMERO());

            Pageable paging =  PageRequest.of(pageNumber - 1, taille, Sort.by("TMNUMERO").descending());
            int start1 = Math.min((int)paging.getOffset(), tHistoriqueMutualistes.size());
            int end1 = Math.min((start1 + paging.getPageSize()), tHistoriqueMutualistes.size());

            Page<THistoriqueMutualiste> page = new PageImpl<>(tHistoriqueMutualistes.subList(start1,end1), paging, tHistoriqueMutualistes.size());

            int current = page.getNumber() + 1;
            int begin = Math.max(1, current - 5);
            int end = Math.min(begin + taille, page.getTotalPages());

            model.addAttribute("list", page);
            model.addAttribute("beginIndex", begin);
            model.addAttribute("endIndex", end);
            model.addAttribute("currentIndex", current);
            model.addAttribute("debut1", debut1);
            model.addAttribute("fin1", fin1);
            model.addAttribute("user", mutualiste.fullname());

        }

        else {

            String debut1 = "2010-01-01";
            String fin1 = "2050-12-31";

            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal();
            PCompte user = pCompteService.findByLoginUser(userDetails.getUsername());
            SMutualiste mutualiste = sMutualisteService.getByMatricule(user.getPMatricule());

            List<THistoriqueMutualiste> tHistoriqueMutualistes = tTransactionmutualisteRepository.findAllHistoriqueMvtMutualiste1(debut1,fin1,mutualiste.getMUNUMERO());

            Pageable paging = PageRequest.of(pageNumber - 1, taille, Sort.by("TMNUMERO").descending());
            int start1 = Math.min((int)paging.getOffset(), tHistoriqueMutualistes.size());
            int end1 = Math.min((start1 + paging.getPageSize()), tHistoriqueMutualistes.size());

            Page<THistoriqueMutualiste> page = new PageImpl<>(tHistoriqueMutualistes.subList(start1,end1), paging, tHistoriqueMutualistes.size());

            int current = page.getNumber() + 1;
            int begin = Math.max(1, current - 5);
            int end = Math.min(begin + taille, page.getTotalPages());

            model.addAttribute("list", page);
            model.addAttribute("beginIndex", begin);
            model.addAttribute("endIndex", end);
            model.addAttribute("currentIndex", current);
            model.addAttribute("debut1", debut1);
            model.addAttribute("fin1", fin1);
            model.addAttribute("user", mutualiste.fullname());
        }

        return "user/historique";
    }

    @GetMapping(value = "/situation")
    public String situation(Model model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        PCompte user = pCompteService.findByLoginUser(userDetails.getUsername());
        SMutualiste mutualiste = sMutualisteService.getByMatricule(user.getPMatricule());

        final int[] total = new int[1];

        List<TSituationMutualiste> tSituationMutualistes = tTransactionmutualisteRepository.findSituationMtualiste(mutualiste.getMUNUMERO());

        tSituationMutualistes.forEach(item -> {
            total[0]+= item.getR_JANV().intValue() + item.getS_JANV().intValue() + item.getR_FEV().intValue() + item.getS_FEV().intValue() + item.getR_MARS().intValue() + item.getS_MARS().intValue() + item.getR_AVRIL().intValue() + item.getS_AVRIL().intValue() + item.getR_MAI().intValue() + item.getS_MAI().intValue() + item.getR_JUIN().intValue() + item.getS_JUIN().intValue() + item.getR_JUIL().intValue() + item.getS_JUIL().intValue() + item.getR_AOUT().intValue() + item.getS_AOUT().intValue() + item.getR_SEPT().intValue() + item.getS_SEPT().intValue() + item.getR_OCT().intValue() + item.getS_OCT().intValue() + item.getR_NOV().intValue() + item.getS_NOV().intValue() + item.getR_DECE().intValue() + item.getS_DECE().intValue();
        });

        model.addAttribute("list", tSituationMutualistes);
        model.addAttribute("total",total[0]);
        model.addAttribute("user", mutualiste.fullname());

        return "user/situation";

    }

    @GetMapping(value = "/aide/{pageNumber}")
    public String aide(@PathVariable Integer pageNumber,Model model) {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        PCompte user = pCompteService.findByLoginUser(userDetails.getUsername());
        SMutualiste mutualiste = sMutualisteService.getByMatricule(user.getPMatricule());

        List<TAideFinanciere> tAideFinancieres = tAideFinanciereService.getByMutualiste(mutualiste.getMUNUMERO());

        int taille = 10;

        Pageable paging = PageRequest.of(pageNumber - 1, taille, Sort.by("AFNUMERO").descending());
        int start1 = Math.min((int)paging.getOffset(), tAideFinancieres.size());
        int end1 = Math.min((start1 + paging.getPageSize()), tAideFinancieres.size());

        Page<TAideFinanciere> page = new PageImpl<>(tAideFinancieres.subList(start1,end1), paging, tAideFinancieres.size());

        int current = page.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + taille, page.getTotalPages());

        model.addAttribute("list", page);
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);
        model.addAttribute("user", mutualiste.fullname());

        return "user/aide";
    }

    @GetMapping(value = "/search-aide/{pageNumber}")
    public String searchaide(@PathVariable Integer pageNumber,@RequestParam Optional<String> statut, @RequestParam Optional<String> ask,Model model) {
        String stat = "";
        String demande = "";
        if(statut.isPresent()) stat = statut.get(); if(ask.isPresent()) demande = ask.get();

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        PCompte user = pCompteService.findByLoginUser(userDetails.getUsername());
        SMutualiste mutualiste = sMutualisteService.getByMatricule(user.getPMatricule());

        List<TAideFinanciere> tAideFinancieres = tAideFinanciereService.getByDateStatut(demande,stat,mutualiste.getMUNUMERO());

        int taille = 10;

        Pageable paging = PageRequest.of(pageNumber - 1, taille, Sort.by("AFNUMERO").descending());
        int start1 = Math.min((int)paging.getOffset(), tAideFinancieres.size());
        int end1 = Math.min((start1 + paging.getPageSize()), tAideFinancieres.size());

        Page<TAideFinanciere> page = new PageImpl<>(tAideFinancieres.subList(start1,end1), paging, tAideFinancieres.size());

        int current = page.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + taille, page.getTotalPages());

        model.addAttribute("list", page);
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);
        model.addAttribute("demande", demande);
        model.addAttribute("stat", stat);
        model.addAttribute("user", mutualiste.fullname());

        return "user/aide1";
    }


    @GetMapping(value = "/search-pret/{pageNumber}")
    public String searchpret(@PathVariable Integer pageNumber,@RequestParam Optional<String> statut, @RequestParam Optional<String> ask,Model model) {
        String stat = "";
        String demande = "";
        if(statut.isPresent()) stat = statut.get(); if(ask.isPresent()) demande = ask.get();

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        PCompte user = pCompteService.findByLoginUser(userDetails.getUsername());
        SMutualiste mutualiste = sMutualisteService.getByMatricule(user.getPMatricule());

        List<TPret> tPrets = tPretService.getByDateStatut(demande,stat,mutualiste.getMUNUMERO());

        int taille = 10;

        Pageable paging = PageRequest.of(pageNumber - 1, taille, Sort.by("PRNUMERO").descending());
        int start1 = Math.min((int)paging.getOffset(), tPrets.size());
        int end1 = Math.min((start1 + paging.getPageSize()), tPrets.size());

        Page<TPret> page = new PageImpl<>(tPrets.subList(start1,end1), paging, tPrets.size());


        int current = page.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + taille, page.getTotalPages());

        model.addAttribute("list", page);
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);
        model.addAttribute("demande", demande);
        model.addAttribute("stat", stat);
        model.addAttribute("user", mutualiste.fullname());

        return "user/pret1";
    }

    @GetMapping(value = "/pret/{pageNumber}")
    public String pret(@PathVariable Integer pageNumber,Model model) {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        PCompte user = pCompteService.findByLoginUser(userDetails.getUsername());
        SMutualiste mutualiste = sMutualisteService.getByMatricule(user.getPMatricule());

        List<TPret> tPrets = tPretService.getByMutualiste(mutualiste.getMUNUMERO());

        int taille = 10;

        Pageable paging = PageRequest.of(pageNumber - 1, taille, Sort.by("PRNUMERO").descending());
        int start1 = Math.min((int)paging.getOffset(), tPrets.size());
        int end1 = Math.min((start1 + paging.getPageSize()), tPrets.size());

        Page<TPret> page = new PageImpl<>(tPrets.subList(start1,end1), paging, tPrets.size());


        int current = page.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + taille, page.getTotalPages());

        model.addAttribute("list", page);
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);
        model.addAttribute("user", mutualiste.fullname());

        return "user/pret";
    }

    @GetMapping(value = "/pret-details/{id}")
    public String pretDetail(@PathVariable Integer id,Model model) {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        PCompte user = pCompteService.findByLoginUser(userDetails.getUsername());
        SMutualiste mutualiste = sMutualisteService.getByMatricule(user.getPMatricule());

        TPret tPret = tPretService.get(id);
        List<TEcheanceRemboursement> echeanceRemboursements = tEcheanceRemboursementRepository.findByPRet(tPret.getPRNUMERO());

        model.addAttribute("pret", tPret);
        model.addAttribute("remboursements", echeanceRemboursements);
        model.addAttribute("user", mutualiste.fullname());

        return "user/pret-detail";
    }

    @GetMapping(value = "/aide-details/{id}")
    public String aideDetail(@PathVariable Integer id,Model model) {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        PCompte user = pCompteService.findByLoginUser(userDetails.getUsername());
        SMutualiste mutualiste = sMutualisteService.getByMatricule(user.getPMatricule());

        TAideFinanciere tAideFinanciere = tAideFinanciereService.get(id);

        model.addAttribute("aide", tAideFinanciere);
        model.addAttribute("user", mutualiste.fullname());

        return "user/aide-detail";
    }

    @GetMapping(value = "/profil")
    public String profil(Model model) {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        PCompte user = pCompteService.findByLoginUser(userDetails.getUsername());
        SMutualiste mutualiste = sMutualisteService.getByMatricule(user.getPMatricule());

        model.addAttribute("profil", mutualiste);
        model.addAttribute("user", mutualiste.fullname());

        return "user/profil";
    }

}
