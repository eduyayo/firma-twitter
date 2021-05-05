package com.pigdroid.twitter.model.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class StatusEntry {

	public static final int MAX_TEXT_TO_STORE = 500;

	@Id
    @GeneratedValue
    @Getter
    @EqualsAndHashCode.Include
    private Long id;

	@Getter
	@Setter
	private String lang;

	@Getter
	@Setter
	private boolean validated;

	@Getter
	@Setter
	private String userName;

	@OneToMany (cascade = CascadeType.ALL)
	@Getter
	@Setter
	private List<Tag> tags;

	@Getter
	@Setter
	@Column(name = "txt",
            length = MAX_TEXT_TO_STORE,
            nullable = false)
	private String text;



}
