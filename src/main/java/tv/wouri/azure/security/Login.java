package tv.wouri.azure.security;

import tv.wouri.azure.models.PCompte;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Login implements UserDetails {

    private String username;
    private String password;
    private Boolean enabled;
    private Boolean locked;
    private List<GrantedAuthority> authorities;
    private PCompte user;

    public Login() {
    }

    public Login(String username) {
        this.username = username;
    }


    public Login(PCompte user) {
        this.username = user.getPMatricule();
        this.password = user.getPPassword();
        this.enabled = user.getPStatus();
        this.locked = user.getPActivated();
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> auths = new ArrayList<>();
        GrantedAuthority auhority = new SimpleGrantedAuthority("ROLE_"+this.user.getPRole().getRLibelle());
        auths.add(auhority);

        return auths;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

}

