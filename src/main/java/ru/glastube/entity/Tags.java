package ru.glas***.entity;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Entity
public class Tags {
    @ManyToOne
    @JoinColumn(name = "id")
    private List<String> tags;

    public void addVideoTag(String tags){
        this.tags.add(tags);
    }


}
