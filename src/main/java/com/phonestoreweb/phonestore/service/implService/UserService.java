package com.phonestoreweb.phonestore.service.implService;

import com.phonestoreweb.phonestore.dto.response.CloudinaryResponse;
import com.phonestoreweb.phonestore.exception.AppException;
import com.phonestoreweb.phonestore.exception.ErrorCode;
import com.phonestoreweb.phonestore.models.Product;
import com.phonestoreweb.phonestore.models.User;
import com.phonestoreweb.phonestore.repositories.UserRepository;
import com.phonestoreweb.phonestore.service.IUserService;
import com.phonestoreweb.phonestore.untils.FileUploadUntil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
// @Slf4j
public class UserService implements IUserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UploadImageService uploadImageService;
    @Override
  //  @PreAuthorize("hasRole('ADMIN')")
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override

    public List<User> getAllUserPageable(Pageable pageable) {
        return userRepository.findAll(pageable).getContent();
    }

    @Override
//    @PostAuthorize("returnObject.username==authentication.name or hasRole('ADMIN')")
    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User getMyInfo() {
        var context = SecurityContextHolder.getContext();
        String username = context.getAuthentication().getName();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        return user;
    }


    @Override
    public User saveUser(User user) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        HashSet<String> roles = new HashSet<>();
        roles.add("USER");

        user.setRoles(roles);
        userRepository.save(user);
        return user;
    }

    @Override
    public void deleteOneUser(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User updateAvatarUser(Long id, MultipartFile imageFile) throws Exception {
        User user = userRepository.findById(id).orElse(null);
        FileUploadUntil.assertAllowed(imageFile,FileUploadUntil.IMAGE_PATTERN);
        String fileName = FileUploadUntil.getFileName(imageFile.getOriginalFilename());
        CloudinaryResponse response = this.uploadImageService.uploadAvatarUser(imageFile,fileName);
        user.setUrlAvatar(response.getUrl());
        return userRepository.save(user);
    }

    @Override
    public boolean userIsExits(String username) {
        User user = userRepository.findByUsername(username).orElse(null);
        if(user!= null){
            return true;
        }
        return false;
    }

    @Override
    public int totalItem() {
        return (int) userRepository.count();
    }

}
