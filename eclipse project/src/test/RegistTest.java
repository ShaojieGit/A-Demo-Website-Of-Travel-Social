package test;
import java.sql.SQLException;

import service.RegistService;
public class RegistTest {
	private RegistService RegistServiceExample;
	public void test_regist() throws SQLException
	{
		RegistServiceExample=new RegistService();
		RegistServiceExample.regist_must("username", "password", "usertype");
	}
}
