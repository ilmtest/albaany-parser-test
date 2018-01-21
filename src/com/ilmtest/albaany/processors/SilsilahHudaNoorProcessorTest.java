package com.ilmtest.albaany.processors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

public class SilsilahHudaNoorProcessorTest
{
	//@Test
	public void process() throws Exception
	{
		SilsilahHudaNoorProcessor test = new SilsilahHudaNoorProcessor();
		test.process(12000);
		Article e = test.getArticles().get(0);

		assertEquals( 1, test.getArticles().size() );
		assertEquals( 11, e.inTapeSegment );
		assertEquals( 273, e.tape );
		assertEquals( 12000, e.pageNumber );
		assertTrue( e.body.contains("سدسها خمسها نصفها ) فليس") );
		assertTrue( e.body.contains("ربه عز وجل نعم.") );
		assertFalse( e.body.contains("2014") );
	}

	@Test
	public void downloadAndSave() throws Exception
	{
		SilsilahHudaNoorProcessor s = new SilsilahHudaNoorProcessor();

		try {
			// 9605
			for (int i = 14418; i <= 17565; i++) {
				s.process(i);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			Connection c = DriverManager.getConnection("jdbc:sqlite:34.db");
			s.write(c);
			c.close();
		}
	}
}
