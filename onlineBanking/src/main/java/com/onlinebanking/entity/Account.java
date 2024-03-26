package com.onlinebanking.entity;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String accountHolderName;
	@NotBlank(message = "Account number is required")
	@Size(min = 10, max = 20, message = "Account number must be between 10 and 20 characters")
	@Pattern(regexp = "\\d+", message = "Account number must contain only digits")
	@Column(unique = true)
	private String accountNumber;
	@Enumerated(EnumType.STRING)
	private Status status;
	private double balance;
	private LocalDate accountOpeningDate;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

}
