package com.coup.app.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@RunWith(MockitoJUnitRunner.class)
public class CalculatorServiceTest {

	@InjectMocks
	private CalculatorService calculatorService;

	@Test
	public void shouldGetFirstAnswerCorrectly() {
		// Given
		List<Integer> scooters = Arrays.asList(15, 10);
		int c = 12;
		int p = 5;

		// When
		int actualValue = calculatorService.calculateNbFE(scooters, c, p);

		// Then
		assertThat(actualValue, is(equalTo(3)));
	}

	@Test
	public void shouldGetSecondAnswerCorrectly() {
		// Given
		List<Integer> scooters = Arrays.asList(11, 15, 13);
		int c = 9;
		int p = 5;

		// When
		int actualValue = calculatorService.calculateNbFE(scooters, c, p);

		// Then
		assertThat(actualValue, is(equalTo(7)));
	}
}
