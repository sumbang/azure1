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
@Table(name = "s_caisse")
public class SCaisse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String CSCODE;
    private String CSLIBELLE;
    private String CSCOMPTE;
    private String CSSTATUT;
    private Boolean CSBLOQUE;
    private Integer ECNUMERO;
    private Double CSSOLDE;
    private Double CSSOLDEREPORT;
    private String CSPLATEFORME;
    private String CSUSER;
    private String CSUSERS;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date CSDATELASTARRETE;
    private Integer CSUSERLASTARRETE;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date CSDATELASTOUVERT;
    private Integer CSUSERLASTOUVERT;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date DATECREATE;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date DATEUPDATE;
    private Integer USERCREATE;
    private Integer USERUPDATE;
    @OneToMany(mappedBy = "CSCODE")
    @JsonIgnore
    @ToString.Exclude
    private List<TPret> pretList;

    @OneToMany(mappedBy = "CSCODE")
    @JsonIgnore
    @ToString.Exclude
    private List<TAideFinanciere> aideFinancieres;
}
