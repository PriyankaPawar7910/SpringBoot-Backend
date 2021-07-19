package com.insurancepolicy.exception;
/**
 * @author priypawa This class contains customized exception
 *         {@link NotFoundException} which extends {@link RuntimeException}. It
 *         contains default and parameterized constructor. It throws an
 *         exception with customized message.
 */
@SuppressWarnings("serial")
public class NotFoundException extends RuntimeException {

	 

    public NotFoundException() {
        // TODO Auto-generated constructor stub
    }
    
    public NotFoundException(String message) {
        super(message);
    }
}
