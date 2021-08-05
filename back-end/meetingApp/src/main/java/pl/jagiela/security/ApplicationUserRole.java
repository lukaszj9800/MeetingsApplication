package pl.jagiela.security;

//typ wyliczeniowy zawierający ograniczony zbiór ról każdego zarejestrowanego użytkownika

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Set;
import java.util.stream.Collectors;
import static pl.jagiela.security.ApplicationUserPermission.USER_WRITE;
import static pl.jagiela.security.ApplicationUserPermission.USER_READ;

public enum ApplicationUserRole {
    ADMIN(Sets.newHashSet(USER_READ, USER_WRITE));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions){
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> grantedAuthorities(){
        Set<SimpleGrantedAuthority> permissions = this.getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        return permissions;
    }
}
