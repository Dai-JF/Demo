package com.dai.boot.bean;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author dai.jf
 * @date 2021/10/15-14:08
 * @description:
 */
@Component
@Data
public class Pet {
  private String name;
  private Double weight;
}
