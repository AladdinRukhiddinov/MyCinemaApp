package uz.pdp.mycinemaapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.mycinemaapp.entity.Attachment;
import uz.pdp.mycinemaapp.entity.AttachmentContent;
import uz.pdp.mycinemaapp.payload.ApiResponse;
import uz.pdp.mycinemaapp.repository.AttachmentContentRepository;
import uz.pdp.mycinemaapp.repository.AttachmentRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AttachmentService {

    private final AttachmentRepository attachmentRepository;
    private final AttachmentContentRepository attachmentContentRepository;

    public ApiResponse fileUpload(MultipartFile file) {
        try {
            Attachment savedAttachment = attachmentRepository.save(new Attachment(file.getOriginalFilename(), file.getSize(), file.getContentType()));
            attachmentContentRepository.save(new AttachmentContent(file.getBytes(), savedAttachment));
            return new ApiResponse("Successfully uploaded!!!", true);
        } catch (IOException e) {
            return new ApiResponse("Error!!!", false);
        }
    }

    public ResponseEntity<ByteArrayResource> fileDownload(Long attachmentId) throws IOException{
        AttachmentContent attachmentContent = attachmentContentRepository.findByAttachmentId(attachmentId);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(attachmentContent.getAttachment().getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + attachmentContent.getAttachment().getOriginalName() + "\"")
                .body(new ByteArrayResource(attachmentContent.getBytes()));
    }

    public ApiResponse getAllAttachment() {
        List<Attachment> attachmentList = attachmentRepository.findAll();
        if (attachmentList.size() != 0) {
            return new ApiResponse("Success", true, attachmentList);
        }
        return new ApiResponse("List empty!", false);

    }

    public ApiResponse getAttachment(Long id) {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (optionalAttachment.isEmpty()) {
            return new ApiResponse("Attachment not found!", false);
        }
        return new ApiResponse("Success!", true, optionalAttachment.get());

    }

    public ApiResponse editAttachment(Long id, MultipartFile file) {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (optionalAttachment.isEmpty()) {
            return new ApiResponse("Attachment not found!", false);
        }
        try {
            Attachment editingAttachment = optionalAttachment.get();
            editingAttachment.setContentType(file.getContentType());
            editingAttachment.setOriginalName(file.getOriginalFilename());
            editingAttachment.setSize(file.getSize());
            Attachment attachment = attachmentRepository.save(editingAttachment);

            AttachmentContent editingAttachmentContent = attachmentContentRepository.getById(editingAttachment.getId());
            editingAttachmentContent.setAttachment(attachment);
            editingAttachmentContent.setBytes(file.getBytes());
            return new ApiResponse("Successfully edited!!", true);
        } catch (Exception e) {
            return new ApiResponse("Error!!!", false);
        }
    }

    public ApiResponse deleteAttachment(Long id) {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (optionalAttachment.isEmpty()) {
            return new ApiResponse("Attachment not found!", false);
        }

        AttachmentContent attachmentContent = attachmentContentRepository.getById(id);
        attachmentContentRepository.deleteById(attachmentContent.getId());
        attachmentRepository.deleteById(id);
        return new ApiResponse("Successfully deleted!!", true);
    }


}
