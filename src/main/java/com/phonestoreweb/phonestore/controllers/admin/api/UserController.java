package com.phonestoreweb.phonestore.controllers.admin.api;

import com.phonestoreweb.phonestore.models.User;
import com.phonestoreweb.phonestore.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("admin/api/v1/user")
public class UserController {

    @Autowired
    private IUserService userService;
    @GetMapping
    public String getUsers(Model model,
                               @RequestParam("limit") int limit,
                               @RequestParam ("page")  int page,
                               @RequestParam("message") String message){
        Pageable pageable = PageRequest.of(page-1,limit);
        model.addAttribute("page",page);
        model.addAttribute("limit",limit);
        model.addAttribute("totalPages",(int) Math.ceil((double)userService.totalItem()/limit));
        model.addAttribute("message",message);

        var authentication =  SecurityContextHolder.getContext().getAuthentication();

        System.out.println("username: "+authentication.getName());
        authentication.getAuthorities().forEach(grantedAuthority -> System.out.println(grantedAuthority.getAuthority()));

        List<User> users = userService.getAllUserPageable(pageable);
        model.addAttribute("users",users);
        return "admin/user";
    }

    @PostMapping
    @ResponseBody
    public User addUser(@RequestBody User user){
        if(userService.userIsExits(user.getUsername())){
            return null;
        }
        return userService.saveUser(user);
    }

    @GetMapping(value = "/findById/{id}")
    @ResponseBody
    public User findUserById(@PathVariable Long id){
        return userService.findUserById(id);
    }
    @PutMapping(value = "/{id}")
    @ResponseBody
    public User updateUser(@RequestBody User user, @PathVariable Long id){
        user.setId(id);
        return userService.saveUser(user);
    }

    @DeleteMapping(value = "/delete/{id}")
    @ResponseBody
    public List<User> deleteOneCategory(@PathVariable Long id){
        userService.deleteOneUser(id);
        return userService.getAllUser();
    }
    @RequestMapping (value = "/image/{id}",method = {RequestMethod.POST, RequestMethod.GET})
    public String updateUserImage(@PathVariable Long id,@RequestParam ("page")  int page,
                                     @RequestParam ("urlAvatar") MultipartFile avatarFile) throws Exception {
        User user = userService.updateAvatarUser(id,avatarFile);
        return "redirect:/admin/api/v1/user?limit=4&message=&page="+page;
    }
}
