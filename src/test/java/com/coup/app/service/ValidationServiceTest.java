package com.coup.app.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(MockitoJUnitRunner.class)
public class ValidationServiceTest {

	@InjectMocks
	private ValidationService validationService;

	@Test
	public void shouldValidateNullScooters() {
		// Given
		Integer[] scooters = null;

		// When
		boolean actual = validationService.isScootersListValid(scooters);

		// Then
		assertThat(actual, is(false));
	}

	@Test
	public void shouldValidateElementsScooters() {
		// Given
		Integer[] scooters = new Integer[1];
		scooters[0] = 10000;

		// When
		boolean actual = validationService.isScootersListValid(scooters);

		// Then
		assertThat(actual, is(false));
	}

	@Test
	public void shouldValidateScooters() {
		// Given
		Integer[] scooters = new Integer[2];
		scooters[0] = 10;
		scooters[1] = 15;

		// When
		boolean actual = validationService.isScootersListValid(scooters);

		// Then
		assertThat(actual, is(true));
	}

	@Test
	public void shouldValidateC() {
		// Given
		Integer c0 = null;
		Integer c1 = -1;
		Integer c2 = 10000;
		Integer c3 = 10;

		// When
		boolean actual0 = validationService.isCValid(c0);
		boolean actual1 = validationService.isCValid(c1);
		boolean actual2 = validationService.isCValid(c2);
		boolean actual3 = validationService.isCValid(c3);

		// Then
		assertThat(actual0, is(false));
		assertThat(actual1, is(false));
		assertThat(actual2, is(false));
		assertThat(actual3, is(true));
	}

	@Test
	public void shouldValidateP() {
		// Given
		Integer p0 = null;
		Integer p1 = -1;
		Integer p2 = 10000;
		Integer p3 = 10;

		// When
		boolean actual0 = validationService.isPValid(p0);
		boolean actual1 = validationService.isPValid(p1);
		boolean actual2 = validationService.isPValid(p2);
		boolean actual3 = validationService.isPValid(p3);

		// Then
		assertThat(actual0, is(false));
		assertThat(actual1, is(false));
		assertThat(actual2, is(false));
		assertThat(actual3, is(true));
	}
}
