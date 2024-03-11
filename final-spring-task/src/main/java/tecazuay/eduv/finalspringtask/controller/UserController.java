package tecazuay.eduv.finalspringtask.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tecazuay.eduv.finalspringtask.models.entity.User;
import tecazuay.eduv.finalspringtask.models.entity.UserDTO;
import tecazuay.eduv.finalspringtask.models.service.ICityService;
import tecazuay.eduv.finalspringtask.models.service.IUserService;

import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private ICityService cityService;

    @RequestMapping(value = "/login")
    public String login(Map<String, Object> model){
        UserDTO userDto = new UserDTO();

        model.put("title", "Iniciar Sesión");
        model.put("userDto", userDto);

        return "form/form-session";
    }

    @PostMapping(value = "/login")
    public String login(UserDTO user, RedirectAttributes flash,HttpSession session){

        if (userService.login(user.getIdcard(), user.getPassword())){
            flash.addFlashAttribute("success","Bienvenido al sistema");
            session.setAttribute("idCard", user.getIdcard());
            return "redirect:/ticket/list-all-tickets";
        }

        flash.addFlashAttribute("danger", "Credenciales incorrectas");

        return "redirect:/user/login";
    }

    @RequestMapping(value = "/logout")
    public String logOut(HttpSession session){

        session.removeAttribute("idCard");

        return "redirect:/ticket/list-all-tickets";
    }

    @GetMapping("/list-all-users")
    public String getAllUsers(Map<String, Object> model) {

        model.put("title", "Lista de usuarios");
        model.put("users", userService.findAll());

        return "table/list-user";
    }

    @RequestMapping(value = "/save-user")
    public String createUser(Map<String, Object> model) {
        User user = new User();

        model.put("title", "Crear nuevo usuario");
        model.put("user", user);
        model.put("cities", cityService.findAll());

        return "table/list-user";
    }

    @PostMapping("/save-user")
    public String saveUser(User user, RedirectAttributes flash) {

        String messageField = (user.getIdCard() != null) ? "Se ha editado los datos del usuario correctamente" : "Se ha creado el usuario correctamente";
        userService.save(user);
        flash.addFlashAttribute("success", messageField);

        return "redirect:/user/list-all-users";
    }

    @GetMapping("/ver-usuario/{id}")
    public String seeDetails(@PathVariable("id") String id, Map<String, Object> model, RedirectAttributes flash) {
        User user = userService.findOne(id);

        if (user == null) {
            flash.addFlashAttribute("danger", "No se ha encontrado al usuario");
            return "redirect:/user/list-all-users";
        }

        model.put("title", "Ver detalles del usuario");
        model.put("user", user);
        model.put("cities", cityService.findAll());

        return "card/card-user";
    }

    @RequestMapping(value = "/save-user/{id}")
    public String editUser(Map<String, Object> model, RedirectAttributes flash, @PathVariable("id") String id) {
        User user;

        if (!id.isEmpty()) {
            user = userService.findOne(id);
            if(user == null) {
                flash.addFlashAttribute("danger", "El usuario no existe");
                return "redirect:/user/list-all-users";
            }
        } else {
            flash.addFlashAttribute("danger", "Ha ocurrido un error");
            return "redirect:/user/list-all-users";
        }

        model.put("title", "Editar usuario");
        model.put("user", user);
        model.put("cities", cityService.findAll());

        return "form/form-user";
    }

    @RequestMapping(value = "/eliminar-usuario/{id}")
    public String deleteCompany(RedirectAttributes flash, @PathVariable("id") String id) {

        try {
            if (!id.isEmpty()) {
                userService.delete(id);
                flash.addFlashAttribute("success", "Se ha eliminado correctamente");
            }
        } catch (Exception e) {
            flash.addFlashAttribute("danger", "No se puede borrar, consulte el administrador de la base de datos");
            return "redirect:/user/list-all-users";
        }

        return "redirect:/user/list-all-users";
    }
}
