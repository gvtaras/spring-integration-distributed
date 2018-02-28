package de.norcom.ingester.entity;

public class FooDto {

	private final String payload;

	public FooDto(String payload) {
		this.payload = payload;
	}

	public String getPayload() {
		return payload;
	}

	@Override
	public String toString() {
		return "FooDto{" +
				"payload='" + payload + '\'' +
				'}';
	}
}
