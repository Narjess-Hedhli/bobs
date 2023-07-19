package com.test.cassandra.service;


import com.test.cassandra.model.User;
import com.test.cassandra.repository.UserRepository;
import jnr.ffi.annotations.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    public User getByIdUser(Integer id) {
        return userRepository.findById(id).orElse(null);}
    public List<User> getAll() {
        return (List<User>) userRepository.findAll();
    }

    public User update(User user) {
        return userRepository.save(user);
    }

    public void delete(Integer id) {
        userRepository.deleteById(id);}

    public void saveBinaryData(Integer idUser, File binaryFile) throws IOException {
        // Read binary data from the file
        byte[] data = new byte[(int) binaryFile.length()];
        try (FileInputStream fis = new FileInputStream(binaryFile)) {
            fis.read(data);
        }

        // Convert byte[] to ByteBuffer
        ByteBuffer byteBuffer = ByteBuffer.wrap(data);

        // Create and save the entity with binary data
        User entity = new User();
        entity.setIdUser(idUser);
        entity.setPhotoUser(byteBuffer);

        userRepository.save(entity);
    }
    public User getEntityById(Integer idUser) {
        return userRepository.findById(idUser).orElse(null);
    }
    public void saveBlob(Integer id, ByteBuffer blobData) {
        User entity = new User();
        entity.setIdUser(id);
        entity.setPhotoUser(blobData);
        userRepository.save(entity);
    }

}
