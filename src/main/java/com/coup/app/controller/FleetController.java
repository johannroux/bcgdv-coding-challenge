package com.coup.app.controller;

import com.coup.app.dto.ErrorResponse;
import com.coup.app.dto.Response;
import com.coup.app.service.CalculatorService;
import com.coup.app.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
@RestController
public class FleetController {

	private CalculatorService calculatorService;
	private ValidationService validationService;

	@Autowired
	public FleetController(CalculatorService calculatorService, ValidationService validationService) {
		this.calculatorService = calculatorService;
		this.validationService = validationService;
	}

	/**
	 * This method serves the required number of fleet engineers (FEs) required to assist the fleet manager (FM).
	 * <p>
	 * This endpoint also provides the following basic validation:
	 * <li>
	 * <ul>There should be exactly 3 parameters: scooters, C, P</ul>
	 * <ul>scooters will contain between 1 and 100 elements.</ul>
	 * <ul>Each element in scooters will be between 0 and 1000.</ul>
	 * <ul>C will be between 1 and 999.</ul>
	 * <ul>P will be between 1 and 1000.</ul>
	 * </li>
	 *
	 * @param scooters the list of the numbers of scooters per district
	 * @param C        the number of scooters a FM can maintain
	 * @param P        the umber of scooters a FE can maintain
	 * @return the number of FEs required to assist the FM
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getNumberRequiredFEs(Integer[] scooters, Integer C, Integer P) {

		boolean scootersListValid = validationService.isScootersListValid(scooters);
		boolean cValid = validationService.isCValid(C);
		boolean pValid = validationService.isPValid(P);

		if (scootersListValid && cValid && pValid) {
			Response response = new Response();
			response.setFleetEngineers(calculatorService.calculateNbFE(Arrays.stream(scooters).collect(Collectors.toList()), C, P));
			return ResponseEntity.ok(response);
		} else {
			ErrorResponse response = new ErrorResponse();
			if (!scootersListValid) {
				response.addError("scooters is not valid");
			}
			if (!cValid) {
				response.addError("C is not valid");
			}
			if (!pValid) {
				response.addError("P is not valid");
			}
			return ResponseEntity.badRequest().body(response);
		}
	}
}
