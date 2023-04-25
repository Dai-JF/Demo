package com.dai.boot.bean;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author dai.jf
 * @date 2021/10/15-14:07
 * @description:
 */
@Component
@ConfigurationProperties(prefix = "person")
@Data
public class Person {

  private String username;
  private Boolean boss;
  private Date birth;
  private Integer age;
  private Pet pet;
  private String[] interests;
  private List<String> animal;
  private Map<String, Object> score;
  private Set<Double> salarys;
  private Map<String, List<Pet>> allPets;
}
