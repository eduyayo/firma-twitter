package com.pigdroid.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;

public class AbstractMockitoTest {

	private AutoCloseable closeable;

	@BeforeEach
	public void setupNocks() {
		this.closeable = MockitoAnnotations.openMocks(this);
	}

	@AfterEach
	public void teardownMocks() throws Exception {
		this.closeable.close();
	}

}
