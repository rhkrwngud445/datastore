package com.db.datastoreserver.domain.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Photo {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String url;

    @Builder
    public Photo(String url) {
        this.url = url;
    }
}
