package uz.pdp.mycinemaapp.entity;

import lombok.*;
import uz.pdp.mycinemaapp.entity.baseEntity.AbsEntity;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "attachment_contents")
public class AttachmentContent extends AbsEntity {

    private byte[] bytes;

    @OneToOne
    private Attachment attachment;

    public AttachmentContent(byte[] data, Attachment attachment) {
        this.bytes = data;
        this.attachment = attachment;
    }
}
