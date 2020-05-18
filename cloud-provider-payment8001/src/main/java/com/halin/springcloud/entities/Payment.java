package com.halin.springcloud.entities;

import lombok.*;
import org.springframework.context.annotation.Bean;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Payment implements Serializable {
    private Long id;
    private String serial;
}
