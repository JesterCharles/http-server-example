package Util.interfaces.servicerepo;

import Model.SiteUser;

/**
 * SiteUser specific interface for all service and repo classes to implement methods to handle deletion and logging in.
 */
public interface SiteUserServiceRepo {
    SiteUser delete(String id);
    SiteUser login(String username, String password);
}
