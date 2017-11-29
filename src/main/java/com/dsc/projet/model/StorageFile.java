/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsc.projet.model;

import java.nio.file.Path;
import java.util.stream.Stream;
import javax.annotation.Resource;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author n
 */
public interface StorageFile {
    void init();

    void store(MultipartFile file);

    Stream<Path> loadAll();

    Path load(String filename);

    Resource loadAsResource(String filename);

    void deleteAll();
    
}
