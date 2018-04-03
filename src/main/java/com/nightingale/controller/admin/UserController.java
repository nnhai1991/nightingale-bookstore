package com.nightingale.controller.admin;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import com.nightingale.Constants;
import com.nightingale.entity.User;
import com.nightingale.model.dto.UserDTO;
import com.nightingale.model.dto.UserForUpdate;
import com.nightingale.model.dto.UserForUpdatePassword;
import com.nightingale.security.CustomUserDetails;
import com.nightingale.service.MailGunEmailService;
import com.nightingale.service.RoleService;
import com.nightingale.service.UserService;
import com.nightingale.util.web.Pagination;
import com.nightingale.util.web.UtilValidation;

@Controller
@RequestMapping("/admin/user")
public class UserController {

    private static final String USER = "user";
    private static final String USER_DTO = "userDTO";
    private static final String PAGINATION = "pagination";
    private static final String KEYWORD = "keyword";
    private static final String ERROR = "error";
    private static final String USERS = "users";
    private static final String ROLES = "roles";

    private final static String FOLDER = "/admin/user";
    private final static String EMAIL_TEMPLATE_FOLDER = "/email-template";
	private static final String CURRENT_TAB = "current_tab";

    @Autowired
    private UserService userService;
    
    @Autowired
    private RoleService roleService;
    
    @Autowired
    private MailGunEmailService mailGunEmailService;

    @Autowired
    private ViewResolver viewResolver;

    @Autowired
    private MessageSource messageSource;

    @Value("${pageSize}")
    private Integer pageSize = 10;


    @GetMapping("")
    public String home(Model model, @RequestParam(required = false, defaultValue = "") String keyword,
                       @RequestParam(required = false, defaultValue = "1") Integer pageNo,
                       Authentication authentication) {

        if (pageNo < 1)
            pageNo = 1;
        boolean isSA = authentication.getAuthorities().stream()
                                     .anyMatch(r -> r.getAuthority().equals("ROLE_" + Constants.Roles.SA));

        Pair<List<UserDTO>, Integer> result = Pair.of(new LinkedList<>(), 0);

        if (UtilValidation.isValidSearch(keyword)) {
            if (isSA) {
                result = userService.getDTOListWithPaginationBySearch(keyword, pageNo, pageSize);
            } else {
                //CustomUserDetails customUser = (CustomUserDetails) authentication.getPrincipal();
                result = userService.getDTOListWithPaginationBySearch(keyword, pageNo, pageSize);
            }
            model.addAttribute(USERS, result.getLeft());

        } else {
            model.addAttribute(ERROR, "invalid_search");
            keyword = "";
        }

        model.addAttribute(KEYWORD, keyword);
        model.addAttribute(PAGINATION, new Pagination(pageNo, result.getRight(), pageSize));
        model.addAttribute(CURRENT_TAB, "user");

        return FOLDER + "/home";
    }

    @GetMapping("/create")
    public String create(Model model, Authentication authentication) {
        //CustomUserDetails customUser = (CustomUserDetails) authentication.getPrincipal();

        model.addAttribute(USER, new User());
        model.addAttribute(ROLES, roleService.getAssignableRoles());

        return FOLDER + "/create";
    }

    @PostMapping("/create")
    public String create(Model model, @Valid User user, BindingResult validResult,
                         Authentication authentication, HttpServletRequest httpServletRequest, Locale locale) {
        CustomUserDetails customUser = (CustomUserDetails) authentication.getPrincipal();

        if (validResult.hasErrors()) {

            model.addAttribute(USER, user);
            model.addAttribute(ROLES, roleService.getAssignableRoles());
            return FOLDER + "/create";

        } else {
            user.setCreatedBy(customUser.getUsername());
            user = userService.create(user);

            if (user!=null) {
                try {

                    model.addAttribute("name",user.getFirstName() + " "+user.getLastName());
                    model.addAttribute("token", userService.createPasswordToken(user.getId()));
                    MockHttpServletResponse mockHttpServletResponse = new MockHttpServletResponse();

                    //use the view reset-password for resetting the password
                    View view = viewResolver.resolveViewName(EMAIL_TEMPLATE_FOLDER + "/user-created", locale);
                    view.render(model.asMap(), httpServletRequest, mockHttpServletResponse);

                    String emailContent = mockHttpServletResponse.getContentAsString();

                    mailGunEmailService.sendEmail(user.getEmail(),messageSource.getMessage("verify_account",null,locale), emailContent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            return "redirect:/user";
        }
    }

    @GetMapping("/details")
    public String details(Model model,
                          @RequestParam(name = "userId", required = true, defaultValue = "-1") Integer userId)
             {

        if (UtilValidation.isValidId(userId)) {

            UserDTO userDTO = userService.readDTO(userId);
            if (userDTO != null) {

                model.addAttribute(USER_DTO, userDTO);
                return FOLDER + "/details";
            }

        }
        return "redirect:/user";

    }

    @GetMapping("/update")
    public String update(Model model,
                         @RequestParam(name = "userId", required = true, defaultValue = "-1") Integer userId,
                         Authentication authentication)
             {
//        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

        if (UtilValidation.isValidId(userId)) {

            User user = userService.read(userId);

            if (user != null) {

                model.addAttribute("userForUpdate", user);
                model.addAttribute(ROLES, roleService.getAssignableRoles());
                return FOLDER + "/update";
            }
        }

        return "redirect:/user";
    }

    @PostMapping("/update")
    public String update(Model model, @Valid UserForUpdate userForUpdate, BindingResult bindingResult,
                         Authentication authentication)  {

        // if no error OR the only error is the password
//        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

        if (!bindingResult.hasErrors()) {
            if (authentication == null)
                authentication = SecurityContextHolder.getContext().getAuthentication();
            userForUpdate.setUpdatedBy(authentication.getName());

            if (userService.update(userForUpdate)) {
                return "redirect:/user";
            } else
                bindingResult.reject("UpdateFailed");
        }

        model.addAttribute("userForUpdate", userForUpdate);
        model.addAttribute(ROLES, roleService.getAssignableRoles());
        return FOLDER + "/update";

    }

    @GetMapping("/delete")
    public String delete(Model model,
                         @RequestParam(name = "userId", required = true, defaultValue = "-1") Integer userId)
             {

        if (UtilValidation.isValidId(userId)) {

            UserDTO userDTO = userService.readDTO(userId);
            if (userDTO != null) {

                model.addAttribute(USER_DTO, userDTO);
                model.addAttribute(USER, userDTO.getUser());
                return FOLDER + "/delete";
            }

        }
        return "redirect:/user";

    }

    @PostMapping("/delete")
    public String delete(@ModelAttribute User user)  {
        if (user != null && UtilValidation.isValidId(user.getId())) {
            User userfromDB = userService.read(user.getId());
            if (userfromDB != null) {
                userService.delete(userfromDB.getId());
            }
        }
        return "redirect:/user";
    }

    @GetMapping("/change-password")
    public String changePassword(Model model,
                                 @RequestParam(name = "userId", required = true, defaultValue = "-1") Integer userId)  {

        if (UtilValidation.isValidId(userId)) {

            User user = userService.read(userId);

            if (user != null) {
            	user.setPassword("");
                model.addAttribute("userForUpdatePassword", user);
                return FOLDER + "/change-password";

            }
        }
        return "redirect:/user";
    }

    @PostMapping("/change-password")
    public String changePassPost(Model model, @Valid UserForUpdatePassword userForUpdatePassword,
                                 BindingResult bindingResult)  {

        if (!bindingResult.hasErrors()) {

            if (userService.updatePassword(userForUpdatePassword)) {
                return "redirect:/user";
            } else {
                bindingResult.reject("UpdateFailed");
            }

        }

        model.addAttribute("userForUpdatePassword", userForUpdatePassword);
        return FOLDER + "/change-password";


    }
}
