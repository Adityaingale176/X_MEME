package com.crio.starter.service;

import com.crio.starter.data.Meme;
import com.crio.starter.repository.MemeRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MemeService {

  Long idCounter = 0L;

  @Autowired
  MemeRepository memeRepository;

  public Meme postMemes(Meme meme) {

    Optional<Meme> optionalMeme = memeRepository.findTopByOrderByIdCounterDesc();
    if (optionalMeme.isEmpty()) {
      meme.setIdCounter(idCounter + 1L);
    } else {
      idCounter = optionalMeme.get().getIdCounter();
      meme.setIdCounter(idCounter + 1L);
    }
    return memeRepository.save(meme);
  }

  public List<Meme> getLatestHundread() {
     
    return memeRepository.findTop100ByOrderByIdCounterDesc();

  }

  public ResponseEntity<?> getMemeById(Long idCounter) {
    
    Optional<Meme> optionalMeme =  memeRepository.findByIdCounter(idCounter);

    if (optionalMeme.isEmpty()) {
      return new ResponseEntity<>("Meme: " + idCounter + " not found",  HttpStatus.NOT_FOUND);
    } else { 
      HashMap<String, String> response = new HashMap<>();
      response.put("id", String.valueOf(optionalMeme.get().getIdCounter()));
      response.put("name", optionalMeme.get().getName());
      response.put("url", optionalMeme.get().getUrl());
      response.put("caption", optionalMeme.get().getCaption());
      return new ResponseEntity<>(response, HttpStatus.OK);
    } 
    
  }

  public void deleteAll() {
    memeRepository.deleteAll();
  }

  public boolean havingConflict(Meme meme) {
     
    Optional<Meme> existingMemeByName = memeRepository.findByName(meme.getName());
    Optional<Meme> existingMemeByUrl = memeRepository.findByName(meme.getUrl());
    Optional<Meme> existingMemeBycaption = memeRepository.findByName(meme.getCaption());

    if (existingMemeByName.isPresent() || existingMemeByUrl.isPresent() 
        || existingMemeBycaption.isPresent()) {
      return true;
    }

    return false;

  } 
    
}
