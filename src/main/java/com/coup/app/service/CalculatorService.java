package com.coup.app.service;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This service is responsible for all the calculations required by the assignment.
 */
@Service
public class CalculatorService {

	/**
	 * Calculates the number of fleet engineers (FE) required to assist the fleet manager (FM) during maintenance,
	 * assuming both the FM and the FEs can only maintain scooters in one district.
	 * <p>
	 * The logic adopted in the method is the following:
	 * <li>
	 * <ul>We first calculate the best district to maintain for the FM. This district is the one in which the FM
	 * can maintain a maximum of scooters (thereby minimizing the number of additional FEs required to help the FM
	 * in his/her district.</ul>
	 * <ul>We then calculate the number FEs that will need to assist the FM in his/her district.</ul>
	 * <ul>Finally we calculate the number of FEs required to maintain the other scooters in the other districts.</ul>
	 * </li>
	 *
	 * @param scooters the list of the numbers of scooters per district
	 * @param c        the number of scooters a FM can maintain
	 * @param p        the umber of scooters a FE can maintain
	 * @return the number of FEs required to assist the FM
	 */
	public int calculateNbFE(List<Integer> scooters, int c, int p) {
		int nbRequiredFE = 0;

		// Get the district for the FM
		int districtForFM = findDistrictForFM(scooters, c);

		// How many FEs are necessary to fill in his district?
		int scootersInFMDistrict = scooters.get(districtForFM);
		nbRequiredFE += Math.ceil(((double) scootersInFMDistrict - c) / p);

		// How many FEs are required for the other districts?
		for (int district = 0; district < scooters.size(); district++) {
			if (district != districtForFM) {
				nbRequiredFE += Math.ceil((double) scooters.get(district) / p);
			}
		}

		return nbRequiredFE;
	}

	/**
	 * Finds the optimal district to maintain for the fleet manager.
	 *
	 * @param scooters the list of the numbers of scooters per district.
	 * @param c        the number of scooters the FM can maintain.
	 * @return the index of the district the FM should maintain
	 */
	private int findDistrictForFM(List<Integer> scooters, int c) {
		int minRest = Integer.MAX_VALUE;
		int bestDistrict = 0;
		for (int district = 0; district < scooters.size(); district++) {
			int scootersInDistrict = scooters.get(district);
			if (scootersInDistrict < c && scootersInDistrict - c < minRest) {
				minRest = scootersInDistrict - c;
				bestDistrict = district;
			}
		}
		return bestDistrict;
	}
}
