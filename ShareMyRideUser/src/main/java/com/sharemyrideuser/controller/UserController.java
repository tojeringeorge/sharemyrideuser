package com.sharemyrideuser.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsLeg;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.DirectionsStep;
import com.google.maps.model.EncodedPolyline;
import com.google.maps.model.LatLng;
import com.sharemyride.dto.ResponseDto;
import com.sharemyride.dto.UserRequestDto;
import com.sharemyride.service.UserService;
import com.sharemyride.validation.Validations;

@Controller
public class UserController {

	@Autowired
	private Validations validations;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/testUrl", method = RequestMethod.GET)
	@ResponseBody
	String testUrl() {
		String origin = "10.008169,76.362285";
		String destination = "10.791030,76.654132";

		System.out.println("test : " + getDirectionsPathFromWebService(origin, destination).size());

		for (LatLng latLng : getDirectionsPathFromWebService(origin, destination)) {
			System.out.println("lat : " + latLng.lat);
			System.out.println("lng : " + latLng.lng);
		}

		return "sucess";

	}

	private List<LatLng> getDirectionsPathFromWebService(String origin, String destination) {
		List<LatLng> path = new ArrayList();

		// Execute Directions API request
		GeoApiContext context = new GeoApiContext.Builder().apiKey("AIzaSyBrPt88vvoPDDn_imh-RzCXl5Ha2F2LYig").build();
		DirectionsApiRequest req = DirectionsApi.getDirections(context, origin, destination);
		try {
			DirectionsResult res = req.await();

			// Loop through legs and steps to get encoded polylines of each step
			if (res.routes != null && res.routes.length > 0) {
				System.out.println("res.routes.length : " + res.routes.length);
				DirectionsRoute route = res.routes[0];

				if (route.legs != null) {
					for (int i = 0; i < route.legs.length; i++) {
						DirectionsLeg leg = route.legs[i];
						if (leg.steps != null) {
							for (int j = 0; j < leg.steps.length; j++) {
								System.out.println("leg.steps.length : " + leg.steps.length);
								DirectionsStep step = leg.steps[j];
								if (step.steps != null && step.steps.length > 0) {
									for (int k = 0; k < step.steps.length; k++) {

										DirectionsStep step1 = step.steps[k];
										EncodedPolyline points1 = step1.polyline;
										if (points1 != null) {
											// Decode polyline and add points to list of route coordinates
											List<com.google.maps.model.LatLng> coords1 = points1.decodePath();
											for (com.google.maps.model.LatLng coord1 : coords1) {
												path.add(new LatLng(coord1.lat, coord1.lng));
											}
										}
									}
								} else {
									EncodedPolyline points = step.polyline;
									if (points != null) {
										// Decode polyline and add points to list of route coordinates
										List<com.google.maps.model.LatLng> coords = points.decodePath();
										System.out.println("coords.size() : " + coords.size());

										for (int k = 0; k < coords.size(); k += 5) {
											path.add(new LatLng(coords.get(k).lat, coords.get(k).lng));
										}

										/*
										 * for (com.google.maps.model.LatLng coord : coords) { path.add(new
										 * LatLng(coord.lat, coord.lng)); }
										 */
									}
								}
							}
						}
					}
				}
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}

		return path;
	}

	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	ResponseEntity<ResponseDto> saveUser(@RequestBody UserRequestDto userRequestDto) {
		ResponseDto responseDto = new ResponseDto();
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.APPLICATION_JSON);

		if (validations.userValidation(userRequestDto).getStatus() != 200) {
			responseDto = validations.userValidation(userRequestDto);
			return ResponseEntity.ok().headers(responseHeaders).body(responseDto);
		} else {
			responseDto = userService.saveUser(userRequestDto);
			return ResponseEntity.ok().headers(responseHeaders).body(responseDto);
		}

	}
}
