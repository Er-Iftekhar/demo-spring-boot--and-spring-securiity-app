package com.app.demospringsecurityappwithamigo.security;

    import com.google.common.collect.Sets;

import java.util.Set;

    import static com.app.demospringsecurityappwithamigo.security.ApplicationUserPermissions.*;

public enum ApplicationEnumRole {
    STUDENT(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(COURSE_READ, COURSE_WRITE, STUDENT_READ, STUDENT_WRITE));

    private final Set<ApplicationUserPermissions> permissions;

    ApplicationEnumRole(Set<ApplicationUserPermissions> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermissions> getPermissions() {
        return permissions;
    }
}
