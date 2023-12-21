package edu.icet.service;

import edu.icet.dao.ProfilePicEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ProfilePicService {

    ProfilePicEntity createImage(MultipartFile file) throws IOException;


    ProfilePicEntity getDefaultProfilePic();
}
