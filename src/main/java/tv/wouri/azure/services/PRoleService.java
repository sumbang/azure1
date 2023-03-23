package tv.wouri.azure.services;

import tv.wouri.azure.models.PRole;
import tv.wouri.azure.repositories.PRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class PRoleService extends AbstractService<PRole, Long> {

    @Autowired
    private PRoleRepository pRoleRepository;

    @Override
    protected JpaRepository<PRole, Long> getRepository() {
        return pRoleRepository;
    }

}
