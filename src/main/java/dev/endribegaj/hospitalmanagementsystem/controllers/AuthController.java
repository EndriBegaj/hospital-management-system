package dev.endribegaj.hospitalmanagementsystem.controllers;


import dev.endribegaj.hospitalmanagementsystem.dtos.LoginRequestDto;
import dev.endribegaj.hospitalmanagementsystem.dtos.UserRegistrationRequestDto;
import dev.endribegaj.hospitalmanagementsystem.exception.EmailExistException;
import dev.endribegaj.hospitalmanagementsystem.exception.UserNameExistException;
import dev.endribegaj.hospitalmanagementsystem.exception.UserNotFoundException;
import dev.endribegaj.hospitalmanagementsystem.exception.WrongPasswordException;
import dev.endribegaj.hospitalmanagementsystem.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginRequestDto", new LoginRequestDto());
        return "auths/login";
    }


    @PostMapping("/login")
    public String login(
            @Valid @ModelAttribute LoginRequestDto loginRequestDto, BindingResult bindingResult
            , HttpServletRequest request
            , HttpServletResponse response
            , @RequestParam(value = "returnUrl", required = false) String returnUrl) {

        if (bindingResult.hasErrors()) {
            return "auths/login";
        }

        try {
            var searchUser = userService.login(loginRequestDto.getUsername(), loginRequestDto.getPassword());
            //me vendose cookie e ri per login
            Cookie cookie = new Cookie("user-id", searchUser.getId().toString());
            if (loginRequestDto.isRememberMe()) {
                cookie.setMaxAge(60 * 60 * 24 * 30); // 30 days
            } else {
                cookie.setMaxAge(60 * 60); // 1 hour
            }
            cookie.setPath("/");
            cookie.setHttpOnly(true);
            cookie.setSecure(false);
            cookie.setDomain("localhost");

            response.addCookie(cookie);

            HttpSession session = request.getSession();
            session.setAttribute("user", searchUser);
            session.setAttribute("role", "ROLE_ADMIN");

            if (returnUrl != null && !returnUrl.isBlank()) {
                return "redirect:" + returnUrl;
            }

            return "redirect:/";
        } catch (UserNotFoundException e) {
            bindingResult.rejectValue("username", "error.loginRequestDto", "User not found");
            return "auths/login";

        } catch (WrongPasswordException e) {
            bindingResult.rejectValue("password", "error.loginRequestDto", "Wrong password");
            return "auths/login";
        }

    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = new Cookie("user-id", "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute("user");
            session.removeAttribute("role");
            session.invalidate();
        }

        return "redirect:/";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("userRegistrationRequestDto", new UserRegistrationRequestDto());
        return "auths/register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute UserRegistrationRequestDto userRegistrationRequestDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "auths/register";
        }
        try {
            var user = userService.register(userRegistrationRequestDto);
        } catch (UserNameExistException e) {
            bindingResult.rejectValue("username", "error.userRegistrationRequestDto", "Username already exists");
            return "auths/register";
        } catch (EmailExistException e) {
            bindingResult.rejectValue("email", "error.userRegistrationRequestDto", "Email already exists");
            return "auths/register";
        }
        return "redirect:/login";
    }
}











