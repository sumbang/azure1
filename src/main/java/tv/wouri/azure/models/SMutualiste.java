package tv.wouri.azure.models;

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
@Table(name = "s_mutualiste")
public class SMutualiste {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer MUNUMERO;
    @ManyToOne
    @JoinColumn(name = "CACODE")
    private SCategorie CACODE;
    private String MUMATRICULE;
    private String MUNOM;
    private String MUPRENOM;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date MUDATENAI;
    private String MULIEUNAI;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date MUDATEEMBAUCHE;
    private String MUCOMPTE;
    private String MUCOMPTEBANCAIRE;
    private String MUSALAIRENETN;
    private String MUSALAIRENETN1;
    private String MUSALAIRENETN2;
    private String MUPLATEFORME;
    private String MUSTATUT;
    private String MUINFOLIBRE;
    private String MUMOTIFACTIF;
    private String MUDESCRIPTIONMOTIF;
    private String MUADDRESSEMAIL;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date DATECREATE;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date DATEUPDATE;
    private Integer USERCREATE;
    private Integer USERUPDATE;
    @OneToMany(mappedBy = "MUNUMERO")
    @JsonIgnore
    @ToString.Exclude
    private List<TPret> pretList;

    @OneToMany(mappedBy = "MUNUMERO")
    @JsonIgnore
    @ToString.Exclude
    private List<TEcheanceRemboursement> echeanceRemboursements;

    @OneToMany(mappedBy = "MUNUMERO")
    @JsonIgnore
    @ToString.Exclude
    private List<TAideFinanciere> aideFinancieres;

    public  String fullname() {
        return this.MUNOM +' '+this.MUPRENOM;
    }
}
