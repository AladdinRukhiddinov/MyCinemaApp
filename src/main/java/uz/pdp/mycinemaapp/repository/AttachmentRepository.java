package uz.pdp.mycinemaapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.mycinemaapp.entity.Attachment;

public interface AttachmentRepository extends JpaRepository<Attachment,Long> {

}
