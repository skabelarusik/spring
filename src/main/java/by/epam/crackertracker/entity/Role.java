/**
 * role user enum
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN, USER, SUPERUSER, CURATOR;

    @Override
    public String getAuthority() {
        return name();
    }
}
