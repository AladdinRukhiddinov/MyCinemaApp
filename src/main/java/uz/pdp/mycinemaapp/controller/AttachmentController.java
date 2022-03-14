package uz.pdp.mycinemaapp.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.mycinemaapp.payload.ApiResponse;
import uz.pdp.mycinemaapp.service.AttachmentService;

@RestController
@RequestMapping("/api/attachment")
@RequiredArgsConstructor
public class AttachmentController {


    private final AttachmentService attachmentService;

    @GetMapping
    public HttpEntity<?> getAllAttachment() {
        ApiResponse apiResponse = attachmentService.getAllAttachment();
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 204).body(apiResponse);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getAttachment(@PathVariable Long id) {
        ApiResponse apiResponse = attachmentService.getAttachment(id);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 409).body(apiResponse);
    }

    @PostMapping
    public HttpEntity<?> fileUpload(@RequestParam MultipartFile file) {
        ApiResponse apiResponse = attachmentService.fileUpload(file);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editAttachment(@PathVariable Long id, @RequestParam MultipartFile file) {
        ApiResponse apiResponse = attachmentService.editAttachment(id, file);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteAttachment(@PathVariable Long id) {
        ApiResponse apiResponse = attachmentService.deleteAttachment(id);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 404).body(apiResponse);
    }
}
