package guru.springframework.repositories;

import guru.springframework.domain.UploadFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<UploadFile, Integer> {
    public UploadFile findOneByFileName(String fileName);
}
