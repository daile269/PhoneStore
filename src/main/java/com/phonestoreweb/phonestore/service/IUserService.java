package com.phonestoreweb.phonestore.service;

import com.phonestoreweb.phonestore.models.User;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IUserService {
    List<User> getAllUser();
    List<User> getAllUserPageable(Pageable pageable);
    User findUserById(Long id);

    User getMyInfo();

    User saveUser(User user);
    void deleteOneUser(long id);

    User updateAvatarUser(Long id, MultipartFile imageFile) throws Exception;

    boolean userIsExits(String username);
    int totalItem();
}
