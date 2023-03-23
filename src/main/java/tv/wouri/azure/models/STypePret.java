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
@Table(name = "s_typepret")
public class STypePret {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String TPCODE;
    private String TPLIBELLE;
    private String TPCODEPAIE;
    private String TPCOMPTE;
    private String TPCOMPTEPRET;
    private String TPCOMPTEINTERET;
    private String TPCOMPTEFRAISGESTION;
    private Integer TPDUREEMAX;
    private Double TPTAUXINTERET;
    private Double TPTAUXFRAISGESTION;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date DATECREATE;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date DATEUPDATE;
    private Integer USERCREATE;
    private Integer USERUPDATE;
    @OneToMany(mappedBy = "TPCODE")
    @JsonIgnore
    @ToString.Exclude
    private List<TPret> pretList;
}
