package tv.wouri.azure.models;

import tv.wouri.azure.config.SecurityConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_echeanceremboursement")
public class TEcheanceRemboursement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ERNUMERO;
    @ManyToOne
    @JoinColumn(name = "PRNUMERO")
    private TPret PRNUMERO;
    private Integer RCNUMERO;
    @ManyToOne
    @JoinColumn(name = "MUNUMERO")
    private SMutualiste MUNUMERO;
    private Double ERMONTANTATTENDU;
    private Double ERMONTANTATTENDUOLD;
    private Double ERMONTANTPAYE;
    private String ERMOIS;
    private String ERMOISID;
    private String ERANNEEID;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ERDATE;
    private String TTCODE;
    private String CSCODE;
    private String BACODE;
    private String ERCOMPTE;
    private String ERCOMPTEPRET;
    private String ERSTATUT;
    private String ERRACHETE;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date ERDATESAI;
    private Integer ERUSERSAI;
    private String ERCOMMENTAIRESAI;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date ERDATEVAL;
    private Integer ERUSERVAL;
    private String ERCOMMENTAIREVAL;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date ERDATEPAI;
    private Integer ERIDLOTRETENUE;
    private Integer ERUSERPAI;
    private Integer EDNUMERO;
    private Boolean ERREVERSE;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date ERDATEREVERSE;
    private Integer ERUSERREVERSE;
    private String ERCOMMENTAIREREVERSE;
    private String ERMODEPAIEMENT;
    private Integer ERNUMEROPAI;
    private Boolean ERCOMPTABILISE;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date ERDATECOMPTA;
    private Integer ERUSERCOMPTA;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date ERDATEREPORT;
    private Integer ERUSERREPORT;
    private String ERCOMMENTAIREREPORT;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date ERDATEIMP;
    private Integer ERUSERIMP;
    private String ERCOMMENTAIREIMP;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date DATECREATE;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date DATEUPDATE;
    private Integer USERCREATE;
    private Integer USERUPDATE;

    public String libellStatut() {
        return SecurityConstants.getStatut(this.getERSTATUT());
    }

    public String libelleRachat() {
        if(this.ERRACHETE == "O") return "Oui";
        else return "Non";
    }
}
