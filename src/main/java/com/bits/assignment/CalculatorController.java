package com.bits.assignment;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorController {

	@GetMapping("/add/{a}/{b}")
	public ResponseEntity<Integer> add(@PathVariable Integer a, @PathVariable Integer b) {
		int sum = a + b;
		return ResponseEntity.ok(sum);
	}

	@GetMapping("/sub/{a}/{b}")
	public ResponseEntity<Integer> sub(@PathVariable Integer a, @PathVariable Integer b) {
		int s = a - b;
		return ResponseEntity.ok(s);
	}

	@GetMapping("/multiply/{a}/{b}")
	public ResponseEntity<Integer> multiply(@PathVariable Integer a, @PathVariable Integer b) {
		int m = a * b;
		return ResponseEntity.ok(m);
	}

	@GetMapping("/divide/{a}/{b}")
	public ResponseEntity<String> divide(@PathVariable Integer a, @PathVariable Integer b) throws ArithmeticException {
		String d;
		if (b == 0) {
			d = "Divisor cannot be 0";
		} else {
			d = (double) a / (double) b + "";
		}

		return ResponseEntity.ok(d);
	}
}
