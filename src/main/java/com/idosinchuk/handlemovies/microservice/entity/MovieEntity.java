package com.idosinchuk.handlemovies.microservice.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity for Movie
 * 
 * @author Igor Dosinchuk
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@Data
@Entity
@Table(name = "movie")
public class MovieEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "movie_id")
	private Long id;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "year", nullable = false)
	private String year;

	@JoinTable(name = "movie_has_actor", joinColumns = @JoinColumn(name = "movie_id", nullable = false), inverseJoinColumns = @JoinColumn(name = "actor_id", nullable = false))
	@ManyToMany(cascade = CascadeType.MERGE)
	private List<ActorEntity> actors;

	@JoinTable(name = "movie_has_genre", joinColumns = @JoinColumn(name = "movie_id", nullable = false), inverseJoinColumns = @JoinColumn(name = "genre_id", nullable = false))
	@ManyToMany(cascade = CascadeType.MERGE)
	private List<GenreEntity> genres;

}
