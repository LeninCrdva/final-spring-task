package tecazuay.eduv.finalspringtask.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tecazuay.eduv.finalspringtask.models.entity.Ticket_Case;
import tecazuay.eduv.finalspringtask.models.service.ICaseService;

import java.util.Map;

@Controller
@RequestMapping("/case/")
public class TicketCaseController {

    @Autowired
    private ICaseService caseService;

    @GetMapping("/list-all-cases")
    public String getAllCases(Map<String, Object> model) {

        model.put("title", "Lista de casos");
        model.put("cases", caseService.findAll());

        return "table/list-case";
    }

    @RequestMapping(value = "/save-case")
    public String createCase(Map<String, Object> model) {
        Ticket_Case cases = new Ticket_Case();

        model.put("title", "Crear un nuevo caso");
        model.put("case", cases);

        return "form/form-case";
    }

    @PostMapping("/save-case")
    public String saveCase(Ticket_Case tcase, RedirectAttributes flash){

        String messageField = (tcase.getCodeCase() != null) ? "Se ha editado de manera correcta" : "Se ha guardado correctamente";
        caseService.save(tcase);
        flash.addFlashAttribute("success", messageField);

        return "redirect:/case/list-all-cases";
    }

    @GetMapping("/ver-caso/{id}")
    public String seeDetails(@PathVariable("id") Long id, Map<String, Object> model, RedirectAttributes flash){
        Ticket_Case tcase = caseService.findOne(id);

        if(tcase == null) {
            flash.addFlashAttribute("danger", "No se ha encontrado el caso");
            return "redirect:/case/list-all-cases";
        }

        model.put("title", "Ver detalle del caso");
        model.put("case", tcase);

        return "card/card-case";
    }

    @RequestMapping(value = "/save-case/{id}")
    public String editCase(Map<String, Object> model, RedirectAttributes flash, @PathVariable("id") Long id) {
        Ticket_Case tcase;

        if(id > 0){
            tcase = caseService.findOne(id);
            if(tcase == null){
                flash.addFlashAttribute("danger", "El caso seleccionado no existe");
                return "redirect:/case/list-all-cases";
            }
        } else {
            flash.addFlashAttribute("danger", "El ID no puede ser 0");
            return "redirect:/case/list-all-cases";
        }

        model.put("title", "Editar caso de ticket");
        model.put("case", tcase);

        return "form/form-case";
    }

    @RequestMapping(value = "/eliminar-caso/{id}")
    public String deleteCompany(RedirectAttributes flash, @PathVariable("id") Long id){

        try {
            if (id > 0) {
                caseService.delete(id);
                flash.addFlashAttribute("success", "Se ha eliminado correctamente");
            }
        } catch (Exception e) {
            flash.addFlashAttribute("danger", "No se puede borrar, consulte el administrador de la base de datos");
            return "redirect:/case/list-all-cases";
        }

        return "redirect:/case/list-all-cases";
    }
}
