package tecazuay.eduv.finalspringtask.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tecazuay.eduv.finalspringtask.models.entity.City;
import tecazuay.eduv.finalspringtask.models.service.ICityService;

import java.util.Map;

@Controller
@RequestMapping("/city")
public class CityController {

    @Autowired
    private ICityService cityService;

    @GetMapping("/list-all-cities")
    public String listCities(Map<String, Object> model) {

        model.put("title", "List all cities");
        model.put("cities", cityService.findAll());

        return "table/list-city";
    }

    @RequestMapping(value = "/save-city")
    public String createTicket(Map<String, Object> model) {
        City city = new City();

        model.put("title", "Crear una nueva ciudad");
        model.put("city", city);

        return "form/form-city";
    }

    @PostMapping("/save-city")
    public String saveTicket(City city, Map<String, Object> model, RedirectAttributes flash) {

        String messageField = (city.getCodeCity() != null) ? "Se ha editado los datos de la ciudad correctamente" : "El registro de la ciudad se ha hecho satisfactoriamente";
        cityService.save(city);
        flash.addFlashAttribute("success", messageField);

        return "redirect:/city/list-all-cities";
    }

    @GetMapping("/ver-ciudad/{id}")
    public String seeDetails(Map<String, Object> model, @PathVariable("id") Long id, RedirectAttributes flash) {
        City city = cityService.findOne(id);

        if (city == null) {
            flash.addFlashAttribute("danger", "No se ha encontrado la ciudad");
            return "redirect:/city/list-all-cities";
        }

        model.put("title", "Ver detalles de ciudad");
        model.put("city", city);

        return "card/card-city";
    }

    @RequestMapping(value = "/save-city/{id}")
    public String editCity(Map<String, Object> model, RedirectAttributes flash, @PathVariable("id") Long id) {
        City city;

        if (id > 0) {
            city = cityService.findOne(id);
            if (city == null) {
                flash.addFlashAttribute("danger", "La ciudad no existe en la base de datos");
                return "redirect:/city/list-all-cities";
            }
        } else {
            flash.addFlashAttribute("danger", "El ID no puede ser 0");
            return "redirect:/city/list-all-cities";
        }

        model.put("title", "Editar ciudad");
        model.put("city", city);

        return "form/form-city";
    }

    @RequestMapping(value = "/eliminar-ciudad/{id}")
    public String deleteCity(RedirectAttributes flash, @PathVariable("id") Long id) {

        try {
            if (id > 0) {
                cityService.delete(id);
                flash.addFlashAttribute("success", "Se ha eliminado correctamente");
            }
        } catch (Exception e) {
            flash.addFlashAttribute("danger", "No se puede borrar, consulte el administrador de la base de datos");
            return "redirect:/city/list-all-cities";
        }

        return "redirect:/city/list-all-cities";
    }

}
