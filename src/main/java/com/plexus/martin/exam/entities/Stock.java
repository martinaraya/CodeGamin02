package com.plexus.martin.exam.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.AccessType;
import org.springframework.data.annotation.AccessType.Type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AccessType(Type.FIELD)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "STOCK")
public class Stock implements Serializable {

	private static final long serialVersionUID = -538397757157701843L;

	@Id
	@Column(name = "SIZE_ID")
	private Integer sizeId;
	
	@Column(name = "QUANTITY")
	private Integer quantity;

	@OneToOne
	@MapsId
	private Size size;

}
