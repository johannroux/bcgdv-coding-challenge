package com.coup.app.service;

import org.springframework.stereotype.Service;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Service
public class ValidationService {

	public boolean isScootersListValid(@NotNull Integer[] scooters) {
		if (scooters == null) {
			return false;
		}
		for (Integer scootersInDistrict : scooters) {
			if (scootersInDistrict == null || scootersInDistrict < 0 || scootersInDistrict > 1000) {
				return false;
			}
		}
		return scooters.length >= 1 && scooters.length <= 100;
	}

	public boolean isCValid(@NotNull @Max(999) @Min(1) Integer c) {
		return c != null && c >= 1 && c <= 999;
	}

	public boolean isPValid(@NotNull @Max(1000) @Min(1) Integer p) {
		return p != null && p >= 1 && p <= 1000;
	}
}
