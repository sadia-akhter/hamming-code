import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;


public class HammingCodeTest {

	/**
	 * Test private encodeNibble method using reflection
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@Test
	public void testPrivateEncodeNibble() throws NoSuchMethodException,
												InvocationTargetException, IllegalAccessException {
		HammingCode hc  = new HammingCode();
		Method method = HammingCode.class.getDeclaredMethod("encodeNibble", byte.class);
		method.setAccessible(true);
		byte input, output;
		
		input = (byte)0; 
		output = (byte) method.invoke(hc, input);
		assertEquals(0, output);
		
		input = (byte)1; 
		output = (byte) method.invoke(hc, input);
		assertEquals(0b01101001, output);
		
		input = (byte)0b00000010; 
		output = (byte) method.invoke(hc, input);
		assertEquals(0b00101010, output);
		
		input = (byte)0b00000011; 
		output = (byte) method.invoke(hc, input);
		assertEquals(0b01000011, output);
		
		input = (byte)0b00000100; 
		output = (byte) method.invoke(hc, input);
		assertEquals(0b01001100, output);
		
		input = (byte)0b00000101; 
		output = (byte) method.invoke(hc, input);
		assertEquals(0b00100101, output);
		
		input = (byte)0b00000110; 
		output = (byte) method.invoke(hc, input);
		assertEquals(0b01100110, output);
		
		input = (byte)0b00001011; 
		output = (byte) method.invoke(hc, input);
		assertEquals(0b00110011, output);		
	}
	/**
	 * Test private decodeNibble method using reflection
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@Test
	public void testPrivateDecodeNibble() throws NoSuchMethodException,
										InvocationTargetException, IllegalAccessException {
		HammingCode hc  = new HammingCode();
		Method method = HammingCode.class.getDeclaredMethod("decodeNibble", byte.class);
		method.setAccessible(true);
		byte input, output;
		
		// test error free decoding 
		input = (byte)0b00110011; 
		output = (byte) method.invoke(hc, input);
		assertEquals(0b1011, output);
		
		// test d1 bit flip
		input = (byte)0b00100011; 
		output = (byte) method.invoke(hc, input);
		assertEquals(0b1011, output);
		
		// test d2 bit flip
		input = (byte)0b00110111; 
		output = (byte) method.invoke(hc, input);
		assertEquals(0b1011, output);
		
		// test d3 bit flip
		input = (byte)0b00110001; 
		output = (byte) method.invoke(hc, input);
		assertEquals(0b1011, output);
		
		// test d4 bit flip
		input = (byte)0b00110010; 
		output = (byte) method.invoke(hc, input);
		assertEquals(0b1011, output);
		
		// test p3 bit flip
		input = (byte)0b00110010; 
		output = (byte) method.invoke(hc, input);
		assertEquals(0b1011, output);
		
		// test p2 bit flip
		input = (byte)0b00010011; 
		output = (byte) method.invoke(hc, input);
		assertEquals(0b1011, output);
		
		// test p1 bit flip
		input = (byte)0b01110011; 
		output = (byte) method.invoke(hc, input);
		assertEquals(0b1011, output);	
	}
	
	/**
	 * Tests both encode() and decode() methods. 
	 */
	@Test
	public void testEncodeDecode() {
		assertEquals(0,HammingCode.encode((byte)0));
		assertEquals(0, HammingCode.decode((byte)0));
		System.out.println("\n");
		
		assertEquals(0b01101001,HammingCode.encode((byte)1));
		assertEquals(1, HammingCode.decode((byte)0b01101001));
		System.out.println("\n");

		assertEquals(0b0011001101101001,HammingCode.encode((byte)177));
		assertEquals(177, HammingCode.decode((byte)0b00110011, (byte)0b01101001));
		System.out.println("\n");
		
		assertEquals(0b0010101001010101,HammingCode.encode((byte)45));
		assertEquals(45, HammingCode.decode((byte)0b00101010, (byte)0b01010101));
		System.out.println("\n");
	}
}
