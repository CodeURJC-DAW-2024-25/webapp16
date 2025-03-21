package com.daw.daw.controller.MVC;

import java.net.URI;
import java.util.Arrays;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.daw.daw.model.Coments;
import com.daw.daw.model.User;
import com.daw.daw.repository.ComentsRepository;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/coments/")
public class ComentsMVCController {

    @Autowired
    private ComentsRepository comentsRepository;


    @Autowired
    private UserMVCController userController;

    @PostMapping("create")
    public String addComent(@RequestParam("rate") int valoracion,
                        @RequestParam("coment") String comentario,
                        String userName,
                        HttpSession session,
                        @RequestParam("id") int eventId,
                        HttpServletResponse response) {
    
    // Comprobar si el usuario está logueado
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    boolean isUserLogged = authentication.isAuthenticated();
    
    if (!isUserLogged) {
        return "redirect:/login";
    }
    Object principal = authentication.getPrincipal();
    String username = "";
    User user = null;

    if (principal instanceof UserDetails) {
        username = ((UserDetails) principal).getUsername();
    } else if (principal instanceof User) {
        username = ((User) principal).getEmail(); // Usa email si es lo que almacenas en User
        user = ((User) principal);
    }
    Coments coments = new Coments(valoracion, comentario, username, eventId);
    comentsRepository.save(coments);
    return "redirect:/";
}
    
    
}