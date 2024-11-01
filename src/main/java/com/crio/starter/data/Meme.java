package com.crio.starter.data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document (collection = "Meme")
public class Meme {
  
  @Id
  private String id;

  private Long idCounter;

  @NotNull
  @NotEmpty
  private String name;

  @NotNull
  @NotEmpty
  private String caption;

  @NotNull
  @NotEmpty
  private String url;    
    
}
