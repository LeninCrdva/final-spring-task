package tecazuay.eduv.finalspringtask.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tecazuay.eduv.finalspringtask.models.entity.Department;
import tecazuay.eduv.finalspringtask.models.service.ICompanyService;
import tecazuay.eduv.finalspringtask.models.service.IDepartmentService;

import java.util.Map;

@Controller
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private IDepartmentService departmentService;

    @Autowired
    private ICompanyService companyService;

    @GetMapping("/list-all-departments")
    public String getAllDepartments(Map<String, Object> model) {

        model.put("title", "Lista de los departamentos");
        model.put("departments", departmentService.findAll());

        return "table/list-department";
    }

    @RequestMapping(value = "/save-department")
    public String createDepartment(Map<String, Object> model) {
        Department department = new Department();

        model.put("department", department);
        model.put("", companyService.findAll());

        return "form/form-department";
    }

    @PostMapping("/save-department")
    public String saveDepartment(Department department, RedirectAttributes flash) {

        String messageField = (department.getCodeDepartment() != null) ? "Se ha editado el departamento correctamente" : "Se ha guardado el departamento correctamente";
        departmentService.save(department);
        flash.addFlashAttribute("success", messageField);

        return "redirect:/department/list-all-departments";
    }

    @GetMapping("/ver-departamento/{id}")
    public String seeDetails(@PathVariable("id") Long id, Map<String, Object> model, RedirectAttributes flash) {
        Department department = departmentService.findOne(id);

        if (department == null) {
            flash.addFlashAttribute("danger", "El departamento seleccionado no existe");
            return "redirect:/department/list-all-departments";
        }

        model.put("title", "Ver detalles del departamento");
        model.put("department", department);

        return "card/card-department";
    }

    @RequestMapping(value = "/save-department/{id}")
    public String editDepartment(Map<String, Object> model, RedirectAttributes flash, @PathVariable("id") Long id) {
        Department department;

        if (id > 0) {
            department = departmentService.findOne(id);
            if (department == null) {
                flash.addFlashAttribute("danger", "No existe el departamento seleccionado");
                return "redirect:/department/list-all-departments";
            }
        } else {
            flash.addFlashAttribute("danger", "El ID no puede ser 0");
            return "redirect:/department/list-all-departments";
        }

        model.put("title", "Editar departamento");
        model.put("department", department);
        model.put("companies", companyService.findAll());

        return "form/form-department";
    }

    @RequestMapping(value = "/eliminar-departamento/{id}")
    public String deleteDepartment(RedirectAttributes flash, @PathVariable("id") Long id, Map<String, Object> model) {
        try {
            if (id>0){
                departmentService.delete(id);
                flash.addFlashAttribute("success", "Se ha eliminado correctamente");
            }
        } catch (Exception e) {
            flash.addFlashAttribute("danger", "No se ha podido eliminar, consulte al administrador de la base de datos");
        }

        return "redirect:/department/list-all-departments";
    }
}
