package com.pigdroid.twitter.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString

@Entity
public class Tag {

	@Id
    @GeneratedValue
    @Getter
    @EqualsAndHashCode.Include
    private Long id;

    @Getter
    @Setter
	private String value;

}
