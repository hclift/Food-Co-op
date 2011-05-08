import java.util.Date;

import javax.swing.JTextField;

/*
 * Handles the format and values for the expiration date among many frames.
 */
public interface ExpirationHandler {
	public void handleExpiration(Date dIn, int iIn, long lIn, JTextField jtfIn);
}
