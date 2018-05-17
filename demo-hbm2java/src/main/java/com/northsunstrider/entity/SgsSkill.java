package com.northsunstrider.entity;
// Generated 2017-10-26 13:17:20 by Hibernate Tools 5.2.3.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * SgsSkill generated by hbm2java
 */
@Entity
@Table(name = "sgs_skill", catalog = "test_db")
public class SgsSkill implements java.io.Serializable {

	private Long id;
	private SgsHero sgsHero;
	private int phase;
	private String description;

	public SgsSkill() {
	}

	public SgsSkill(int phase, String description) {
		this.phase = phase;
		this.description = description;
	}

	public SgsSkill(SgsHero sgsHero, int phase, String description) {
		this.sgsHero = sgsHero;
		this.phase = phase;
		this.description = description;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "hero_")
	public SgsHero getSgsHero() {
		return this.sgsHero;
	}

	public void setSgsHero(SgsHero sgsHero) {
		this.sgsHero = sgsHero;
	}

	@Column(name = "phase", nullable = false)
	public int getPhase() {
		return this.phase;
	}

	public void setPhase(int phase) {
		this.phase = phase;
	}

	@Column(name = "description", nullable = false)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
