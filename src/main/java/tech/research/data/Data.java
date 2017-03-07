package tech.research.data;

import lombok.AllArgsConstructor;

import java.io.Serializable;

@lombok.Data
@AllArgsConstructor
public class Data implements Serializable{
    private String key;
    private Integer value;
}
