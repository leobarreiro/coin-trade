package org.javaleo.cointrade.server.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

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
@EqualsAndHashCode
@Entity
@Table(name = "exchange", uniqueConstraints = {
		@UniqueConstraint(name = "uk_exchange_name", columnNames = { "name" }) })
@SequenceGenerator(name = "seqExchange", sequenceName = "exchange_seq", allocationSize = 1, initialValue = 1)
public class Exchange implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqExchange")
	@Column(name = "exchange_id")
	@JsonIgnore
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "description")
	@JsonIgnore
	private String description;

	@Column(name = "url")
	private String url;

	@Column(name = "public_api")
	@JsonIgnore
	private String publicApiUrl;

	@Column(name = "private_api")
	@JsonIgnore
	private String privateApiUrl;

	@Column(name = "api_key")
	@JsonIgnore
	private String apiKey;

	@Column(name = "api_secret")
	@JsonIgnore
	private String apiSecret;

}
