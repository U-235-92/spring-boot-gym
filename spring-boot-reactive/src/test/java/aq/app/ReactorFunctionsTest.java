package aq.app;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;

class ReactorFunctionsTest {

	@Test
	@Disabled
	void printStringFlux() {
//		You can also create a flux using Flux.from methods
		Flux<String> fruitFlux = Flux.just("Apple", "Orange", "Grape", "Banana", "Strawberry");
		fruitFlux.subscribe(System.out::println);
	}

	@Test
	@Disabled
	void verifyStringFlux() {
		Flux<String> fruitFlux = Flux.just("Apple", "Orange", "Grape", "Banana", "Strawberry");
		StepVerifier.create(fruitFlux)
			.expectNext("Apple") 
			.expectNext("Orange") 
			.expectNext("Grape") 
			.expectNext("Banana") 
			.expectNext("Strawberry") 
			.verifyComplete();
	}
	
	@Test
	@Disabled
	void printRangeFlux() {
		Flux<Integer> intFlux = Flux.range(1, 5);
		intFlux.subscribe(System.out::println);
	}
	
	@Test
	@Disabled
	void printIntervalFlux() {
		Flux<Long> longFlux = Flux.interval(Duration.ofSeconds(1L)).take(5);
		StepVerifier.create(longFlux)
			.expectNext(0L) 
			.expectNext(1L) 
			.expectNext(2L) 
			.expectNext(3L) 
			.expectNext(4L)
			.verifyComplete();
	}
	
	@Test
	@Disabled
	void mergeFluxes() {
		Flux<String> carFlux = Flux.just("Lamborghini", "BMW")
				.delayElements(Duration.ofMillis(500));
		Flux<String> countryFlux = Flux.just("Italy", "Germany")
				.delaySubscription(Duration.ofMillis(250))
				.delayElements(Duration.ofMillis(500));
		Flux<String> mergedFlux = carFlux.mergeWith(countryFlux);
		StepVerifier.create(mergedFlux)
			.expectNext("Lamborghini")
			.expectNext("Italy")
			.expectNext("BMW")
			.expectNext("Germany")
			.verifyComplete();
	}
	
	@Test
	@Disabled
	void zipFluxes() {
		Flux<String> carFlux = Flux.just("Lamborghini", "BMW")
				.delayElements(Duration.ofMillis(500));
		Flux<String> countryFlux = Flux.just("Italy", "Germany")
				.delaySubscription(Duration.ofMillis(250))
				.delayElements(Duration.ofMillis(500));
		Flux<String> zippedFlux = Flux.zip(carFlux, countryFlux, (car, country) -> String.join(" is made in ", car, country));
		StepVerifier.create(zippedFlux)
			.expectNext("Lamborghini is made in Italy")
			.expectNext("BMW is made in Germany")
			.verifyComplete();
	}
	
	@Test
	@Disabled
	public void buffer() {
		Flux<String> fruitFlux = Flux.just("apple", "orange", "banana", "kiwi", "strawberry");
		Flux<List<String>> bufferedFlux = fruitFlux.buffer(3);
		StepVerifier.create(bufferedFlux)
			.expectNext(Arrays.asList("apple", "orange", "banana"))
			.expectNext(Arrays.asList("kiwi", "strawberry"))
			.verifyComplete();
	}
	
	@Test
	public void bufferAndFlatMap() throws Exception {
		Flux.just("apple", "orange", "banana", "kiwi", "strawberry")
			.buffer(3)
			.flatMap(fruits -> Flux
					.fromIterable(fruits)
					.map(name -> name.toUpperCase())
					.subscribeOn(Schedulers.parallel())
					.log())
			.subscribe();
	}
}
