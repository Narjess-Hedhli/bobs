package com.test.cassandra.controller;


import com.test.cassandra.model.User;
import com.test.cassandra.service.UserService;
import jnr.ffi.annotations.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.ByteBuffer;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Integer id) {
        return userService.getByIdUser(id);
    }

    @PostMapping("/")
    public User createUser(@RequestBody User user) {
        return userService.save(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@RequestBody User user) {
        return userService.update( user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.delete(id);
    }

    @GetMapping("/get/{entityId}")
    public ResponseEntity<byte[]> getPhotoUser(@PathVariable Integer idUser) {
        User entity = userService.getEntityById(idUser);
        if (entity == null) {
            return ResponseEntity.notFound().build();
        }

        // Convert ByteBuffer to byte[]
        byte[] binaryData = entity.getPhotoUser().array();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "filename.bin");

        return new ResponseEntity<>(binaryData, headers, HttpStatus.OK);
    }
    @PostMapping("/uploadBlob")
    public ResponseEntity<String> uploadBlob(@RequestParam Integer id, @RequestParam ByteBuffer blobData) {
        try {
            userService.saveBlob(id, blobData);
            return ResponseEntity.ok("Blob uploaded successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading blob: " + e.getMessage());
        }
    }
}
