package tv.wouri.azure.models;

import tv.wouri.azure.forms.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "web_compte")
public class PCompte extends AbstractModel<Long> {
    @Column(unique=true)
    @NotEmpty
    private String PMatricule;
    @NotEmpty
    private String PNom;
    private String PPrenom;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date PDatNaiss;
    @ManyToOne
    @JoinColumn(name = "PRole")
    private PRole PRole;
    @Column(unique=true)
    @NotEmpty
    @Email
    private String PEmail;
    @NotEmpty
    @JsonIgnore
    private String PPassword;
    private Boolean PStatus;
    private Boolean PActivated;
    private String PResettoken;
    private String PActivedtoken;
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private Date PAddedDate;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date PUpdateDate;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date PActivedDate;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date PActivedExpirationDate;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date PTokenExpirationDate;

    public PCompte(User user) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = sdf.parse(user.getPDatNaiss());
        this.PMatricule = user.getPMatricule();
        this.PDatNaiss = date1;
        this.PRole = user.getPRole();
        this.PEmail = user.getPEmail();
        this.PPassword = user.getPPassword();
        this.PStatus = user.getPStatus();
        this.PActivated = user.getPActivated();
        this.PResettoken = user.getPResettoken();
        this.PActivedtoken = user.getPActivedtoken();
    }

}
