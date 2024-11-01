package com.crio.starter.controller;

import com.crio.starter.data.Meme;
import com.crio.starter.service.MemeService;
import java.util.HashMap;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/memes/")
public class MemeController {

  @Autowired
  MemeService memeService;

  @PostMapping()
  public ResponseEntity<?> postMemes(@Valid @RequestBody Meme meme) {

    if (memeService.havingConflict(meme)) {
      return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
    Meme savedMeme =  memeService.postMemes(meme);

    HashMap<String, String> response = new HashMap<>();
    response.put("id", String.valueOf(savedMeme.getIdCounter()));

    return new ResponseEntity<>(response,HttpStatus.CREATED);

  }   

  @GetMapping()
  public ResponseEntity<?> getLatestHundreadMemes() {
        
    List<Meme> memes = memeService.getLatestHundread();

    return new ResponseEntity<>(memes, HttpStatus.OK);

  }  

  @GetMapping("{id}")
  public ResponseEntity<?> getMemeById(@PathVariable Long id) {

    return memeService.getMemeById(id);

  }

  @DeleteMapping("/delete")
  public ResponseEntity<?> deleteAll() {
    memeService.deleteAll();
    return new ResponseEntity<>("All Memes deleted", HttpStatus.ACCEPTED);
  } 

    
}
