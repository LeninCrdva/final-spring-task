package tecazuay.eduv.finalspringtask.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tecazuay.eduv.finalspringtask.models.entity.Company;
import tecazuay.eduv.finalspringtask.models.service.ICityService;
import tecazuay.eduv.finalspringtask.models.service.ICompanyService;
import tecazuay.eduv.finalspringtask.models.service.IDepartmentService;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private ICompanyService companyService;

    @Autowired
    private IDepartmentService departmentService;

    @Autowired
    private ICityService cityService;

    @GetMapping("/list-all-companies")
    public String getAllCompanies(Map<String, Object> model) {

        model.put("title", "Lista de Empresas");
        model.put("companies", companyService.findAll());

        return "table/list-company";
    }

    @RequestMapping(value = "/save-company")
    public String createCompany(Map<String, Object> model) {
        Company company = new Company();

        model.put("title", "Crear una nueva compañia");
        model.put("company", company);
        model.put("cities", cityService.findAll());

        return "form/form-company";
    }

    @PostMapping("/save-company")
    public String saveCompany(Company company, RedirectAttributes flash) {

        String messageField = (company.getCodeCompany() != null) ? "Se ha editado los datos de la compañia correctamente" : "Se ha creado la compañia correctamente";
        companyService.save(company);
        flash.addFlashAttribute("success", messageField);

        return "redirect:/company/list-all-companies";
    }

    @GetMapping("/ver-empresa/{id}")
    public String seeDetails(@PathVariable("id") Long id, Map<String, Object> model, RedirectAttributes flash) {
        Company company = companyService.findOne(id);

        if (company == null) {
            flash.addFlashAttribute("danger", "No se ha encontrado la compañia");
            return "redirect:/company/list-all-companies";
        }

        model.put("title", "Ver detalles de la compañia");
        model.put("company", company);
        model.put("departments", departmentService.findAllDepartmentsByCompanyId(id));

        return "card/card-company";
    }

    @RequestMapping(value = "/save-company/{id}")
    public String editCompany(Map<String, Object> model, RedirectAttributes flash, @PathVariable("id") Long id) {
        Company company;

        if (id > 0) {
            company = companyService.findOne(id);
            if (company == null) {
                flash.addFlashAttribute("danger", "La empresa no existe");
                return "redirect:/company/list-all-companies";
            }
        } else {
            flash.addFlashAttribute("danger", "El ID no puede ser 0");
            return "redirect:/company/list-all-companies";
        }

        model.put("title", "Editar empresa");
        model.put("company", company);
        model.put("cities", cityService.findAll());

        return "form/form-company";
    }

    @RequestMapping(value = "/eliminar-empresa/{id}")
    public String deleteCompany(RedirectAttributes flash, @PathVariable("id") Long id) {

        try {
            if (id > 0) {
                companyService.delete(id);
                flash.addFlashAttribute("success", "Se ha eliminado correctamente");
            }
        } catch (Exception e) {
            flash.addFlashAttribute("danger", "No se puede borrar, consulte el administrador de la base de datos");
            return "redirect:/company/list-all-companies";
        }

        return "redirect:/company/list-all-companies";
    }
}
