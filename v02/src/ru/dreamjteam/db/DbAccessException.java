package ru.dreamjteam.db;

import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author Senya
 */
public class DbAccessException extends Exception {
	public DbAccessException(Throwable cause) {
		super(cause);
	}
}
