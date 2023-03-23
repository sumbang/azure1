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
@Table(name = "s_categorie")
public class SCategorie {
    @Id
    private String CACODE;
    private String CALIBELLE;
    @OneToMany(mappedBy = "CACODE")
    @JsonIgnore
    @ToString.Exclude
    private List<SMutualiste> mutualistes;
    private Integer CANUMEROCAT;
    private Double CASALAIREBASE;
    private Double CATAUXCOTISATIONRETRAITE;
    private Double CATAUXCOTISATIONAIDE;
    private String CACOMPTERETRAITE;
    private String CACOMPTESECOURS;
    private String CACOMPTELIAISON;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd h:i:s")
    private Date DATECREATE;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd h:i:s")
    private Date DATEUPDATE;
    private Integer USERCREATE;
    private Integer USERUPDATE;

    public String toString() {
        return this.CALIBELLE;
    }
}
