package Rest.Model;

import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Arrays;

@Entity
public class PhotoModel {

    @Id
    @GeneratedValue
    private Long id;

//    @Type(type = "org.hibernate.type.BlobType")
//    @Lob
    private byte[] image;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "PhotoModel{" +
                "id=" + id +
                ", image=" + Arrays.toString(image) +
                '}';
    }
}
