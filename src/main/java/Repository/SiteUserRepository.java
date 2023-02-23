package Repository;

import Model.SiteUser;
import Util.interfaces.servicerepo.SiteUserServiceRepo;
import Util.interfaces.servicerepo.GenericServiceRepo;

import java.util.ArrayList;

public class SiteUserRepository implements GenericServiceRepo<SiteUser>, SiteUserServiceRepo {
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
