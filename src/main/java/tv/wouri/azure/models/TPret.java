package tv.wouri.azure.models;

import tv.wouri.azure.config.SecurityConstants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_pret")
public class TPret {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer PRNUMERO;
    @ManyToOne
    @JoinColumn(name = "TPCODE")
    private STypePret TPCODE;
    private String PRMODE;
    private String BACODE;
    @ManyToOne
    @JoinColumn(name = "CSCODE")
    private SCaisse CSCODE;
    @ManyToOne
    @JoinColumn(name = "MUNUMERO")
    private SMutualiste MUNUMERO;
    private String PTCODE;
    private String PROBJET;
    private String TTCODE;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date PRDATEDEMANDE;
    private Double PRMONTANTACCORDE;
    private Double PRMONTANTDEMANDE;
    private Double PRMONTANTPLAFONNE;
    private Double PRTAUXINTERET;
    private Double PRINTERET;
    private Double PRTAUXFRAISGESTION;
    private Double PRFRAISGESTION;
    private Double PRNETADEBLOQUER;
    private Double PRMONTANTDEBLOQUE;
    private String PDMOISPREMIEREECHEANCE;
    private String PRDATEDERNIEREECHEANCE;
    private Integer PRNBREECHEANCE;
    private Double PRNBREECHEANCESOUHAITE;
    private String PRSTATUT;
    private String PRRACHAT;
    private Integer PRNNUMERORACHAT;
    private Double PRMONTANTRACHAT;
    private Double PRDUREERACHAT;
    private Double PRMONTANTINITRACHAT;
    private Double PRDUREEINITRACHAT;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date PRDATESAI;
    private int PRUSERSAI;
    private String PRCOMMENTAIRESAI;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date PRDATEVAL;
    private Integer PRUSERVAL;
    private String PRMODEPAIEMENT;
    private String PRMODEREMBOURSEMENT;
    private String PRCOMMENTAIREVAL;
    private String PRMOISPREMIEREECHEANCE;
    private String PRCOMPTE;
    private String PRCOMPTEPRET;
    private String PRCOMPTEFRAISGESTION;
    private String PRCOMPTEINTERET;
    private Boolean PRTRANSFERE;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date PRDATETRANSFERE;
    private Integer PRUSERTRANSFERE;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date PRDATEPAI;
    private Integer PRUSERPAI;
    private String PRCOMMENTAIREPAI;
    private Integer PRNUMEROPAI;
    private String PRPLATEFORMEPAIEMENT;
    private boolean PRCOMPTABILISE;
    private String PRDATECOMPTA;
    private Integer PRUSERCOMPTA;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date PRDATEANN;
    private String PRCOMMENTAIREANN;
    private Integer PRUSERANN;
    private Integer PRCUMULE;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date PRDATECUMULE;
    private Integer PRUSERCUMULE;
    private String PROBJETCUMUL;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date PRDATERESTAURE;
    private Integer PRUSERRESTAURE;
    private String PROBJETRESTAURE;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date DATECREATE;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date DATEUPDATE;
    private Integer USERCREATE;
    private Integer USERUPDATE;
    @OneToMany(mappedBy = "PRNUMERO")
    @JsonIgnore
    @ToString.Exclude
    private List<TEcheanceRemboursement> echeanceRemboursements;

    public String libellStatut() {
        return SecurityConstants.getStatut(this.getPRSTATUT());
    }
}
