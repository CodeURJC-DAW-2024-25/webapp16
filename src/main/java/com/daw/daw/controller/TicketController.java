package com.daw.daw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.daw.daw.model.Ticket;
import com.daw.daw.model.User;
import com.daw.daw.repository.TicketRepository;
import com.daw.daw.service.PdfService;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@Controller
public class TicketController {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserController userController;

    @Autowired
    private PdfService pdfService;

    @PostMapping("/tickets/buyTicket")
    public String createTicket(
            @RequestParam("ticketName") String nombreTicket,
            @RequestParam("dni") String DNI,
            @RequestParam("gender") String genero,
            @RequestParam("eventName") String nombreEvento,
            @RequestParam("category") String categoria,
            HttpServletResponse response) throws IOException {

        // Comprobar si el usuario está logueado
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isUserLogged = authentication.isAuthenticated();

        if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal().equals("anonymousUser")) {
            return "redirect:/paginaDetalleConcierto";
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

        Ticket ticket = new Ticket(username, DNI, nombreTicket, nombreEvento, genero, username, categoria,
                LocalDateTime.now());
        ticketRepository.save(ticket);

        byte[] pdfBytes = pdfService.generarPdfTicket(ticket);

        // Configurar la respuesta HTTP para que sea una descarga
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=ticket_" + ticket.getUserOwner() + ".pdf");
        response.getOutputStream().write(pdfBytes);
        response.flushBuffer();

        return "redirect:/";
    }

    @GetMapping("/tickets/download/{id}")
    public void downloadTicket(@PathVariable Long id, HttpServletResponse response) throws IOException {
        Optional<Ticket> optionalTicket = ticketRepository.findById(id);

        if (optionalTicket.isPresent()) {
            Ticket ticket = optionalTicket.get();
            byte[] pdfBytes = pdfService.generarPdfTicket(ticket);

            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=ticket_" + ticket.getUserOwner() + ".pdf");
            response.getOutputStream().write(pdfBytes);
            response.flushBuffer();
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Ticket no encontrado");
        }
    }

}