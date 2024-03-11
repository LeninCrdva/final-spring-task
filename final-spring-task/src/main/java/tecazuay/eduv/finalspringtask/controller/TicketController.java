package tecazuay.eduv.finalspringtask.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tecazuay.eduv.finalspringtask.models.entity.Attached_file;
import tecazuay.eduv.finalspringtask.models.entity.Ticket;
import tecazuay.eduv.finalspringtask.models.entity.User;
import tecazuay.eduv.finalspringtask.models.service.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Map;

@Controller
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    private ITicketService ticketService;

    @Autowired
    private IAttachedFileService attachedFileService;

    @Autowired
    private IDepartmentService departmentService;

    @Autowired
    private IUserService userService;

    @Autowired
    private ICaseService ticketCaseService;

    @Value("${spring.config.location}")
    private String URL;

    @RequestMapping(value = {"/list-all-tickets", "", "/", "/index", "/inicio"})
    public String getTickets(Map<String, Object> model, HttpSession session) {
        String requestSession = (String) session.getAttribute("idCard");

        if (requestSession == null || requestSession.isEmpty()) {
            return "redirect:/user/login";
        }

        model.put("title", "List All tickets");
        model.put("session", requestSession);
        model.put("tickets", ticketService.findAll());

        return "table/list-ticket";
    }

    @RequestMapping(value = "/save-ticket")
    public String createTicket(Map<String, Object> model, HttpSession session) {

        Ticket ticket = new Ticket();
        Attached_file file = new Attached_file();
        String requestSession = (String) session.getAttribute("idCard");

        model.put("title", "Open new ticket");
        model.put("ticket", ticket);
        model.put("files", file);
        model.put("user", userService.findOne(requestSession));
        model.put("departments", departmentService.findAll());
        model.put("ticketCases", ticketCaseService.findAll());

        return "form/form-ticket";
    }

    @PostMapping("/save-ticket")
    public String saveTicket(Ticket ticket, RedirectAttributes flash, @RequestParam(value = "file", required = false) MultipartFile file, HttpSession session) {

        if (!file.isEmpty()) {
            try {
                Path RutaArchivo = Paths.get(URL + "//" + file.getOriginalFilename());
                Files.write(RutaArchivo, file.getBytes());
                flash.addFlashAttribute("info", "Se ha subido correctamente la foto '" + file.getOriginalFilename() + "'");

            } catch (IOException e) {
                flash.addFlashAttribute("danger", "Error al guardar la foto: " + e.getMessage());
                return "redirect:/ticket/list-all-tickets";
            }

        }

        User user = userService.findOne((String) session.getAttribute("idCard"));
        ticket.setUserIdCard(user);
        System.out.println(ticket);

        Long idTicket = ticketService.save(ticket);
        ticket.setCodeTicket(idTicket);
        Attached_file newFile = new Attached_file();
        newFile.setCodeTicket(ticket);
        newFile.setFileName(file.getOriginalFilename());
        flash.addFlashAttribute("success", "Se ha guardado la foto");

        attachedFileService.save(newFile);

        return "redirect:/ticket/list-all-tickets";
    }

    @GetMapping("/ver-ticket/{id}")
    private String seeDetails(@PathVariable("id") Long id, Map<String, Object> model, RedirectAttributes flash) {

        Ticket ticket = ticketService.findOne(id);
        Attached_file file = attachedFileService.findOneByTicket(id);

        if (ticket == null) {
            flash.addFlashAttribute("danger", "El ticket no existe en la base de datos");
            return "redirect:/ticket/list-all-tickets";
        }

        model.put("title", "Ver ticket N°: " + ticket.getCodeTicket());
        model.put("ticket", ticket);
        model.put("file", file);

        return "card/card-ticket";
    }
}
