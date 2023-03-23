package tv.wouri.azure.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "web_role")
public class PRole extends AbstractModel<Long> {
    @NotEmpty
    private String RLibelle;
    @OneToMany(mappedBy = "PRole")
    @JsonIgnore
    @ToString.Exclude
    private List<PCompte> comptes;
}
