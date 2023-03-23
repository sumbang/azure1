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
@Table(name = "t_aidefinanciere")
public class TAideFinanciere {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer AFNUMERO;
    private String BACODE;
    @ManyToOne
    @JoinColumn(name = "CSCODE")
    private SCaisse CSCODE;
    @ManyToOne
    @JoinColumn(name = "MUNUMERO")
    private SMutualiste MUNUMERO;
    private String ADNUMERO;
    @ManyToOne
    @JoinColumn(name = "TACODE")
    private STypeAide TACODE;
    private String AFSTATUT;
    private Double AFMONTANT;
    private String TTCODE;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date AFDATESAI;
    private String AFCOMMENTAIRESAI;
    private Integer AFUSERSAI;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date AFDATEVAL;
    private String AFMODEPAIEMENT;
    private String AFCOMPTE;
    private String AFCOMPTESECOURS;
    private Integer AFUSERVAL;
    private String AFCOMMENTAIREVAL;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date AFDATEPAI;
    private Integer AFNUMEROPAI;
    private Integer AFUSERPAI;
    private String AFCOMMENTAIREPAI;
    private String AFPLATEFORMEPAIEMENT;
    private Boolean AFCOMPTABILISE;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date AFDATECOMPTA;
    private Integer AFUSERCOMPTA;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date AFDATEANN;
    private Integer AFUSERANN;
    private String AFCOMMENTAIREANN;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date DATECREATE;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date DATEUPDATE;
    private Integer USERCREATE;
    private Integer USERUPDATE;

    public String libellStatut() {
        return SecurityConstants.getStatut(this.getAFSTATUT());
    }
}
