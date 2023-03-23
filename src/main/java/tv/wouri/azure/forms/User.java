package tv.wouri.azure.forms;

import tv.wouri.azure.models.PRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String PMatricule;
    private String PDatNaiss;
    private PRole PRole;
    private String PEmail;
    private String PPassword;
    private String PPassword1;
    private Boolean PStatus;
    private Boolean PActivated;
    private String PResettoken;
    private String PActivedtoken;

}
