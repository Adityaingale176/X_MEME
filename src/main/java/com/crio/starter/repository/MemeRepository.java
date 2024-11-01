package com.crio.starter.repository;

import com.crio.starter.data.Meme;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MemeRepository extends MongoRepository<Meme, String> {

  Meme save(Meme meme);

  List<Meme> findAll(Sort sort);

  Optional<Meme> findById(String id);

  Optional<Meme> findByIdCounter(Long idCounter);

  Optional<Meme> findTopByOrderByIdCounterDesc();

  List<Meme> findTop100ByOrderByIdCounterDesc();

  void deleteAll();

  Optional<Meme> findByName(String name);

  Optional<Meme> findByCaption(String caption);

  Optional<Meme> findByUrl(String url);
  
}
