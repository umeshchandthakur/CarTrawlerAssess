package com.cartrawler.assessment.bean;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.cartrawler.assessment.car.CarResult;

public class CarResultProcessorImplTest {
	
	@Test
    public void processCarResultTest() {
		CarResultProcessor carResultProcessor = mock(CarResultProcessor.class);
		List<CarResult> carList = new ArrayList<>();
		when(carResultProcessor.processCarResult(anySet())).thenReturn(carList);
		
	}
	
	@Test
    public void processCarResultTest2() {
		CarResultProcessor carResultProcessor = mock(CarResultProcessor.class);
		List<CarResult> carList = new ArrayList<>();
		when(carResultProcessor.processCarResult(anyList())).thenReturn(carList);
	}
	
}
