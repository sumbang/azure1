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
@Table(name = "s_typeaide")
public class STypeAide {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String TACODE;
    private String TALIBELLE;
    private String TACOMPTE;
    private String TACOMPTESECOURS;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date DATECREATE;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date DATEUPDATE;
    private Integer USERCREATE;
    private Integer USERUPDATE;
    @OneToMany(mappedBy = "TACODE")
    @JsonIgnore
    @ToString.Exclude
    private List<TAideFinanciere> aideFinancieres;
}
