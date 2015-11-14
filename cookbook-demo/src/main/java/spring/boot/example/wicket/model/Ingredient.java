package spring.boot.example.wicket.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Ingredient implements Serializable {

	public enum Unit {
		PIECE, GRAMM, KG, LITER, TEA_SPOON, SPOON, KNIFE_TIP
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private Float amount;
	@Enumerated(EnumType.STRING)
	private Unit unit;
	private String name;
	@ManyToOne()
	private Recipe recipe;
	
	public Ingredient(float amount) {
		this.amount = amount;
	}

	public static List<Unit> getUnits() {
		return Arrays.asList(Unit.values());
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
