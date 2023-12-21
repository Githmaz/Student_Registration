package edu.icet.service.impl;

import edu.icet.dao.ProfilePicEntity;
import edu.icet.repository.ProfilePicRepository;
import edu.icet.service.ProfilePicService;
import edu.icet.utility.ProfilePicUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service

public class ProfilePicServiceImpl implements ProfilePicService {

    @Autowired
    private ProfilePicRepository profilePicRepository;

    // Create a new profile picture entity from the provided image file.
    @Override
    public ProfilePicEntity createImage(MultipartFile file) throws IOException {
        return profilePicRepository.save(ProfilePicEntity.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .image(ProfilePicUtility.compressImage(file.getBytes())).build());
    }

    // Get the default profile picture entity.
    public  ProfilePicEntity getDefaultProfilePic(){
        return  profilePicRepository.save(ProfilePicUtility.getDefaultProfilePicEntity());
    }



}
