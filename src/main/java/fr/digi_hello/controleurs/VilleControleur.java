package fr.digi_hello.controleurs;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import fr.digi_hello.DTO.VilleDTO;
import fr.digi_hello.DTO.VilleMapper;
import fr.digi_hello.exceptions.VilleException;
import fr.digi_hello.classes.Ville;
import fr.digi_hello.services.VilleService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/villes")
public class VilleControleur {

    @Autowired
    private VilleService villeService;

    @GetMapping("")
    public List<VilleDTO> findAll() {
        return listVilleToDTO(villeService.extractVilles());
    }

    @GetMapping("nomLike/{nom}")
    public List<VilleDTO> findByNomLike(@PathVariable String nom) throws VilleException {
        return listVilleToDTO(villeService.findByNomLike(nom));
    }
    
    @GetMapping(path = "greaterThan/{nb}")
    public List<VilleDTO> findGreater(@PathVariable int nb) {
        return listVilleToDTO(villeService.findGreater(nb));
    }

    @GetMapping(path = "greaterThan/{nb}/export")
    public void findGreaterExport(@PathVariable int nb, HttpServletResponse response) throws IOException, DocumentException {
        Document document = new Document(PageSize.A4);
        response.setHeader("Content-Type", "attachment; filename=\"fichier.pdf\"");
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        document.addTitle("Villes");
        document.newPage();
        BaseFont baseFont = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, BaseFont.EMBEDDED);
        List<Ville> listeVille = villeService.findGreater(nb);
        for ( Ville ville : listeVille) {
//          String uri = "https://geo.api.gouv.fr/departements/" + ville.getCodeDepartement() + "?fields=nom,code,codeRegion";
//          String data = new RestTemplate().getForObject(uri, String.class);
//            JSONParser parse = new JSONParser(data);
//            JSONObject json_data = parse.parse();
            Phrase p = new Phrase("Nom de la ville : " + ville.getNom()
                    + "\nNombre d'habitant : " + ville.getNbHabitants()
                    + "\nCode Departement : " + ville.getDepartement().getCode()
                    + "\nNom Departement : " +
                    "\n-------\n"
            , new Font(baseFont, 32.0f,1, new BaseColor(0, 51, 80)));
            document.add(p);
        }

        document.close();
        response.flushBuffer();
    }

    @GetMapping(path = "between/{min},{max}")
    public List<VilleDTO> findBetween(@PathVariable int min, @PathVariable int max) {
        return listVilleToDTO(villeService.findBetween(min, max));
    }

    @GetMapping(path = "DepartementGreater/{code},{min}")
    public List<VilleDTO> findDepartementGreater(@PathVariable String code, @PathVariable int min) {
        return listVilleToDTO(villeService.findDepartementGreater(code, min));
    }

    @GetMapping(path = "DepartementBetween/{code},{min},{max}")
    public List<VilleDTO> findDepartementBetween(@PathVariable String code, @PathVariable int min, @PathVariable int max) {
        return listVilleToDTO(villeService.findDepartementBetween(code, min, max));
    }

    @GetMapping(path = "/id/{id}")
    public VilleDTO getVilleById(@PathVariable int id) {
        return VilleMapper.toDto(villeService.extractVilleId(id));
    }

    @GetMapping(path = "/name/{name}")
    public VilleDTO getVilleByName(@PathVariable String name) {
        return VilleMapper.toDto(villeService.extractVilleName(name));
    }

    @GetMapping(path = "/findPageable/{id},{size}")
    public List<VilleDTO> findPageable(@PathVariable String id, @PathVariable int size) {
        return listVilleToDTO(villeService.findPageable(id, size));
    }

    @PostMapping
    public ResponseEntity<String> addVille(@RequestBody Ville ville) throws VilleException {
        villeService.insertVille(ville);
        return ResponseEntity.ok("Ville added");
    }

//    @PutMapping(path = "update/{id}")
//    public ResponseEntity<String> updateVille(@PathVariable("id") int id, @Valid @RequestBody Ville villes, BindingResult result) {
//        this.villeService.save(villes);
//        return ResponseEntity.ok("Ville mise a jour avec succes");
//    }

    @DeleteMapping(path = "delete/{id}")
    public ResponseEntity<String> deleteVille(@PathVariable int id) {
        villeService.deleteVille(id);
        return ResponseEntity.ok("Ville deleted");
    }

    private List<VilleDTO> listVilleToDTO(Iterable<Ville> listVille){
        List<VilleDTO> listVilleDTO = new ArrayList<>();
        for (Ville ville : listVille) {
            listVilleDTO.add(VilleMapper.toDto(ville));
        }
        return listVilleDTO;
    }
}
