package com.msg.wmTestHelper.exception;

/**
 * TestHelper specific Runtime exception for various errors along the way.
 *
 * @author Dennis Rippinger
 * @since 18.01.16
 */
public class TestHelperException extends RuntimeException {

	public TestHelperException(String message) {
		super(message);
	}
}
