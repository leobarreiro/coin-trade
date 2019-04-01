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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPublicApiUrl() {
		return publicApiUrl;
	}

	public void setPublicApiUrl(String publicApiUrl) {
		this.publicApiUrl = publicApiUrl;
	}

	public String getPrivateApiUrl() {
		return privateApiUrl;
	}

	public void setPrivateApiUrl(String privateApiUrl) {
		this.privateApiUrl = privateApiUrl;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getApiSecret() {
		return apiSecret;
	}

	public void setApiSecret(String apiSecret) {
		this.apiSecret = apiSecret;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Exchange other = (Exchange) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
