package uz.pdp.mycinemaapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.mycinemaapp.entity.Director;
import uz.pdp.mycinemaapp.payload.ApiResponse;
import uz.pdp.mycinemaapp.service.DirectorService;

@RestController
@RequestMapping("/api/director")
@RequiredArgsConstructor
public class DirectorController {

    private final DirectorService directorService;

    @GetMapping
    public HttpEntity<?> getAllDirectors(){
        ApiResponse allDirectors = directorService.getAllDirectors();
        return ResponseEntity.status(allDirectors.isStatus()?200:204).body(allDirectors);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getDirector(@PathVariable Long id){
        ApiResponse apiResponse = directorService.getDirector(id);
        return ResponseEntity.status(apiResponse.isStatus()?200:404).body(apiResponse);
    }

    @PostMapping()
    public HttpEntity<?> addDirector(@RequestPart("file") MultipartFile file, @RequestPart("json-director") Director director){
        ApiResponse apiResponse = directorService.addDirector(file, director);
        return ResponseEntity.status(apiResponse.isStatus()?200:409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editDirector(@PathVariable Long id,@RequestPart("file") MultipartFile file, @RequestPart("json-director") Director director){
        ApiResponse apiResponse = directorService.editDirector(id, file, director);
        return ResponseEntity.status(apiResponse.isStatus()?200:409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> addDirector(@PathVariable Long id){
        ApiResponse apiResponse = directorService.deleteDirector(id);
        return ResponseEntity.status(apiResponse.isStatus()?200:409).body(apiResponse);
    }

}
