package Service;

import Model.SiteUser;
import Util.interfaces.servicerepo.GenericServiceRepo;
import Util.interfaces.servicerepo.SiteUserServiceRepo;

import java.util.ArrayList;

public class SiteUserService implements GenericServiceRepo<SiteUser>, SiteUserServiceRepo {
    @Override
    public ArrayList<SiteUser> findAll() {
        return null;
    }

    @Override
    public SiteUser create(SiteUser newObject) {
        return null;
    }
    @Override
    public SiteUser findByID() {
        return null;
    }

    @Override
    public boolean update(SiteUser updatedObject) {
        return false;
    }

    @Override
    public SiteUser delete(String id) {
        return null;
    }

    @Override
    public SiteUser login(String username, String password) {
        return null;
    }
}
