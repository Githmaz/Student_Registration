  package edu.icet.service.impl;

import edu.icet.dao.GuardianEntity;
import edu.icet.dto.Guardian;
import edu.icet.repository.GuardianRepository;
import edu.icet.service.GuardianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuardianServiceImpl implements GuardianService {
    @Autowired
    private GuardianRepository guardianRepository;

    //add a new Guardian
    @Override
    public GuardianEntity createGuardian(Guardian guardian) {
        return guardianRepository.save(new GuardianEntity(guardian.getGuardianId(),guardian.getFirstName(), guardian.getLastName(), guardian.getPhoneNumber(), guardian.getCareer()));
    }

}

