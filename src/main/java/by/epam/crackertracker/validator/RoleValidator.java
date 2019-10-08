/**
 * it's a class for validate role user
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.validator;

import by.epam.crackertracker.entity.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleValidator implements ValidatorI{
    @Override
    public boolean isValidate(String str) {
        boolean status = false;
        if((str != null && !str.isEmpty()) && (str.equalsIgnoreCase(Role.USER.toString()) ||
                str.equalsIgnoreCase(Role.SUPERUSER.toString()) || str.equalsIgnoreCase(Role.ADMIN.toString()) ||
                str.equalsIgnoreCase(Role.CURATOR.toString()))){
                status = true;
        }
        return status;
    }
}
