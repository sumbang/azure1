package tv.wouri.azure.controllers;

import tv.wouri.azure.config.EmailDetails;
import tv.wouri.azure.config.SecurityConstants;
import tv.wouri.azure.forms.User;
import tv.wouri.azure.models.*;
import tv.wouri.azure.repositories.TTransactionmutualisteRepository;
import tv.wouri.azure.services.*;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("setting")
public class SettingController {

    @Autowired
    private PCompteService pCompteService;

    @Autowired
    private PRoleService pRoleService;

    @Autowired
    private SMutualisteService sMutualisteService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TTransactionmutualisteRepository tTransactionmutualisteRepository;

    @GetMapping("/reset")
    public String reset(Model model) {
        ResetRequest reset = new ResetRequest();
        model.addAttribute("reset", reset);
        return "setting/reset";
    }

    @PostMapping(value = "/ask")
    public String ask(ResetRequest reset, final RedirectAttributes ra) {

        PCompte user = pCompteService.findByLoginUser(reset.getUsername());

        if(user == null) {
            ra.addFlashAttribute("errorFlash", "Aucun compte trouvé avec ce login");
            return "redirect:/setting/reset";
        }

        else {

            if(user.getPRole().getId() == 1) {
                ra.addFlashAttribute("errorFlash", "Vous n'êtes pas autorisé à utiliser cette interface");
                return "redirect:/setting/reset";
            }

            else {

                String token = SecurityConstants.randomString(8);
                user.setPResettoken(token);
                pCompteService.update(user);

                String url = SecurityConstants.APPS_URL+"/setting/token/"+token;

                // send email/sms with resetToken
                EmailDetails emailDetails = new EmailDetails();
                emailDetails.setRecipient(user.getPEmail());
                emailDetails.setSubject("Réinitialisation du mot de passe");
                emailDetails.setMsgBody("Bonjour "+user.getPNom()+" "+user.getPPrenom()+",\n\rVous avez initié une demande de réinitialisation de votre mot de passe.\n\rVeuillez saisir le code de réinialisation suivant pour terminer : "+token+" \n\rCordialement");

                String retour = emailService.sendSimpleMail(emailDetails);

                if(retour.equals("Error while Sending Mail")) {
                    ra.addFlashAttribute("errorFlash", "Impossible d'envoyer le mail de réinitialisation");
                    return "redirect:/setting/reset";
                }

                else {
                    ra.addFlashAttribute("successFlash", "Mail de reinitialisation envoyée.");
                    return "redirect:/setting/token";
                }

            }
        }
    }

    @GetMapping("/token")
    public String token(Model model) {
        TokenResetRequest token1 = new TokenResetRequest();
        model.addAttribute("token1", token1);
        return "setting/change";
    }

    @PostMapping(value = "/change")
    public String change(TokenResetRequest token, final RedirectAttributes ra) throws ParseException {

        PCompte user = pCompteService.findByResetToken(token.getToken());

        if (user == null) {
            ra.addFlashAttribute("errorFlash", "Aucune correspondance pour ce code de réinitialisatio");
            return "redirect:/setting/change";

        } else {

            if(user.getPRole().getId() == 1) {
                ra.addFlashAttribute("errorFlash", "Vous n'êtes pas autorisé à utiliser cette interface");
                return "redirect:/setting/change";
            }

            else if(!token.getPassword().equals(token.getPassword1())) {
                ra.addFlashAttribute("errorFlash", "Vos mots de passe ne correspondent pas");
                return "redirect:/setting/change";
            }

            else {

                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");
                LocalDateTime localDate = LocalDateTime.now();
                String date = dtf.format(localDate);
                Date date1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);

                user.setPPassword(passwordEncoder.encode(token.getPassword()));
                user.setPResettoken(null);
                user.setPUpdateDate(date1);
                pCompteService.update(user);

                // email notification for password change
                EmailDetails emailDetails = new EmailDetails();
                emailDetails.setRecipient(user.getPEmail());
                emailDetails.setSubject("Changement de mot de passe");
                emailDetails.setMsgBody("Bonjour " + user.getPNom()+" "+user.getPPrenom() + ",\n\rVous venez de changer avec succès votre mot de passe. \n\rSi cette opération ne venait pas de vous, veuillez immédiatement nous contacter. \n\rCordialement");

                String retour = emailService.sendSimpleMail(emailDetails);

                if (retour.equals("Error while Sending Mail")) {
                    ra.addFlashAttribute("errorFlash", "Impossible d'envoyer le mail de réinitialisation");
                    return "redirect:/setting/change";
                } else {
                    ra.addFlashAttribute("successFlash", "Enregistrement réussie.");
                    return "redirect:/login";
                }
            }
        }

    }


    @GetMapping("/signup")
    public String signup(Model model) {
        User user;
        if(model.containsAttribute("createur")) {
           user = ((User) model.getAttribute("createur"));
        } else  user = new User();
        model.addAttribute("user", user);
        return "setting/signup";
    }

    @PostMapping(value = "/nouveau")
    public String nouveau(User personne, final RedirectAttributes ra, Model model) throws ParseException {

        PCompte user = new PCompte(personne);

        SMutualiste mutualiste = sMutualisteService.getByMatricule(user.getPMatricule());
        String erreuM = "Aucune correspondance pour ce matricule mutualiste";

        int result = -1;
        if (mutualiste != null) {
            result = mutualiste.getMUDATENAI().compareTo(user.getPDatNaiss());
        }

        if (mutualiste == null) {
            ra.addFlashAttribute("errorFlash", erreuM);
            model.addAttribute("createur", personne);
            return "redirect:/setting/signup";
        }

        else if(!mutualiste.getMUADDRESSEMAIL().equals(user.getPEmail()) ) {
            ra.addFlashAttribute("errorFlash", erreuM);
            model.addAttribute("createur", personne);
            return "redirect:/setting/signup";
        }

        else if(result != 0) {
            ra.addFlashAttribute("errorFlash", erreuM);
            model.addAttribute("createur", personne);
            return "redirect:/setting/signup";
        }

        else if (!mutualiste.getMUSTATUT().equals("00001")) {
            ra.addFlashAttribute("errorFlash", "Votre profil mutualiste doit être actif afin de créer un compte");
            model.addAttribute("createur", personne);
            return "redirect:/setting/signup";
        }

        else if (!personne.getPPassword().equals(personne.getPPassword1())) {
            ra.addFlashAttribute("errorFlash", "Vos mots de passes ne correspondent pas");
            model.addAttribute("createur", personne);
            return "redirect:/setting/signup";
        }

        else {

            PCompte exist = pCompteService.findByLoginUser(user.getPEmail());
            PCompte exist2 = pCompteService.findByMatricule(user.getPMatricule());

            if (exist != null) {
                ra.addFlashAttribute("errorFlash", "Un compte existe déjà avec cette adresse email");
                model.addAttribute("createur", personne);
                return "redirect:/setting/signup";
            }

            else if (exist2 != null) {
                ra.addFlashAttribute("errorFlash", "Un compte existe déjà avec ce matricule mutualiste");
                model.addAttribute("createur", personne);
                return "redirect:/setting/signup";
            }

            else if (!SecurityConstants.validatePassword(user.getPPassword())) {
                ra.addFlashAttribute("errorFlash", "Votre mot de passe ne respecte pas les règles sécuritaires : 8 caractères minimum, au moins un caractère majuscule, au moins un caractère minuscule et un caractère spécial");
                model.addAttribute("createur", personne);
                return "redirect:/setting/signup";
            }

            else {

                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");
                LocalDateTime localDate = LocalDateTime.now().plusHours(SecurityConstants.TOKEN_ACTIVATION_TIME);
                String date = dtf.format(localDate);
                Date date1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);      // adds one hour

                String token = SecurityConstants.randomString(8);
                String url = SecurityConstants.APPS_URL+"/setting/activate/"+token;
                PRole role = pRoleService.get(2L);
                user.setPPassword(passwordEncoder.encode(user.getPPassword()));
                user.setPActivated(false);
                user.setPStatus(true);
                user.setPRole(role);
                user.setPActivedtoken(token);
                user.setPActivedExpirationDate(date1);
                user.setPNom(mutualiste.getMUNOM());
                user.setPPrenom(mutualiste.getMUPRENOM());
                pCompteService.save(user);

                EmailDetails emailDetails = new EmailDetails();
                emailDetails.setRecipient(user.getPEmail());
                emailDetails.setSubject("Activation de votre compte");
                emailDetails.setMsgBody("Bonjour "+user.getPNom()+" "+user.getPPrenom()+",\n\rVous venez de créer un compte sur le portail web Sygem.\n\rAfin de le rendre actif, veuillez cliquez sur le lien suivant : "+url+" \n\rCordialement");

                String retour = emailService.sendSimpleMail(emailDetails);

                if(retour.equals("Error while Sending Mail")) {
                    ra.addFlashAttribute("errorFlash", "Impossible d'envoyer le mail d'activation");
                    return "redirect:/login";
                }

                else {
                    ra.addFlashAttribute("successFlash", "Votre compte sur le portail a bien été crée, un mail d'activation vous a été envoyé.");
                    return "redirect:/login";
                }

            }

        }
    }

    @GetMapping("/activate/{token}")
    public String activate(@PathVariable String token, final RedirectAttributes ra) throws ParseException {
        PCompte user = pCompteService.findByActivatedToken(token);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");
        LocalDateTime localDate = LocalDateTime.now();
        String date = dtf.format(localDate);
        Date date1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);

        if (user == null) {
            ra.addFlashAttribute("errorFlash", "Token d'activation expiré ou inexistant");
            return "redirect:/login";
        }
        else if(user.getPActivated()) {
            ra.addFlashAttribute("errorFlash", "Ce compte est déjà activé");
            return "redirect:/login";
        }

        else if(date1.after(user.getPActivedExpirationDate())) {
            pCompteService.delete(user.getId());
            ra.addFlashAttribute("errorFlash", "Votre lien d'activation est expiré, vous devez faire une nouvelle souscription.");
            return "redirect:/setting/signup";
        }

        else {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            user.setPActivedDate(date1);
            user.setPUpdateDate(date1);
            user.setPActivated(true);
            user.setPActivedtoken(null);
            pCompteService.save(user);
            ra.addFlashAttribute("successFlash", "Compte activé avec succès");
            return "redirect:/login";
        }

    }

    @GetMapping("/export")
    public void export(@RequestParam Optional<String> d, @RequestParam Optional<String> f, HttpServletResponse response) throws DocumentException, IOException {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        PCompte user = pCompteService.findByLoginUser(userDetails.getUsername());
        SMutualiste mutualiste = sMutualisteService.getByMatricule(user.getPMatricule());

        List<THistoriqueMutualiste> tHistoriqueMutualistes = tTransactionmutualisteRepository.findAllHistoriqueMvtMutualiste1(d.get(),f.get(),mutualiste.getMUNUMERO());

        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=data_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        HistoriquetoPdf exporter = new HistoriquetoPdf(tHistoriqueMutualistes);
        exporter.export(response);

    }

}
