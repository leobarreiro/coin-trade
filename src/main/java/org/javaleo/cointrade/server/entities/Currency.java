package org.javaleo.cointrade.server.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.javaleo.cointrade.server.enums.Symbol;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@EqualsAndHashCode
@Table(name = "currency", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "symbol" }, name = "uk_currency_symbol") })
@SequenceGenerator(name = "seqCurrency", sequenceName = "currency_seq", allocationSize = 1, initialValue = 1)
public class Currency {

	@Id
	@Column(name = "currency_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqCurrency")
	@JsonIgnore
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(name = "symbol", length = 30)
	private Symbol name;

	@Column(name = "description", nullable = true, length = 60)
	@JsonIgnore
	private String description;

}
