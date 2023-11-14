package com.ecommerce.dao;

import com.ecommerce.entity.Upload;
import org.springframework.data.repository.CrudRepository;

public interface UploadRepo extends CrudRepository<Upload, Long> {
}
