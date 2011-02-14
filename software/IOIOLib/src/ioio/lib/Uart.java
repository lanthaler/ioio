package ioio.lib;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Uart {

	// Define some known good constants? 
	public static final int NO_PARITY = 0;
	public static final int ODD_PARITY = 1;
	public static final int EVEN_PARITY = 2;

	public static final int BAUD_2400 = 2400;
	public static final int BAUD_4800 = 4800;
	public static final int BAUD_9600 = 9600;
	public static final int BAUD_19200 = 19200;
	public static final int BAUD_28800 = 28800;
	public static final int BAUD_33600 = 33600;
	
	public static final float ONE_STOP_BIT = 1;
	public static final float TWO_STOP_BITS = 2;
	public static final float ONE_AND_HALF_STOP_BITS = 1.5f; 
		
	private int baud = 0; // Support all standard bauds (or send in divisor and support arbitrary?)
	private int parity = 0; // even/odd/no
	private float stop_bits = 0; // 1/1.5/2
	private int data_bits = 8;	// 7/8/9
	private IOIOPin rx;
	private IOIOPin tx;
	
	public Uart(int rx, int tx, int baud, int parity, float stop) {		
		this.stop_bits = stop;
		this.parity = parity;
		this.baud = baud;
		this.rx = new IOIOPin(rx);
		this.tx = new IOIOPin(tx);
	}
	
	public DataOutputStream openDataOutputStream() {
		return new DataOutputStream(openOutputStream());
	}
	
	public DataInputStream openDataInputStream() {
		return new DataInputStream(openInputStream());
	}
	
	public UARTOutputStream openOutputStream() {
		return new UARTOutputStream(this);
	}
	
	public UARTInputStream openInputStream() {
		return new UARTInputStream(this);
	}
		
	int readByte() {
		return -1;
	}
	
	void writeByte(int val) {
		
	}
	
	public class UARTOutputStream extends OutputStream {

		Uart parent;
		
		protected UARTOutputStream(Uart uart) {	
			parent = uart;
		}
		
		@Override
		public void write(int val) throws IOException {
			parent.writeByte(val);			
		}	
	}

	public class UARTInputStream extends InputStream {
		
		Uart parent;
		
		protected UARTInputStream(Uart uart) {			
			parent = uart;
		}
		
		@Override
		public int read() throws IOException {
			return parent.readByte();
		}		
	}
}