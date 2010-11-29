package de.visualdependencies.util.translator;

import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AbstractDataTranslatorTest {

	private AbstractDataTranslator translator;

	@Before
	public void setUp() throws Exception {
		translator = new AbstractDataTranslator() {};
	}

	@Test
	public void testBasics() {
		Assert.assertFalse("Contains returned wrong result for a nonexisting key.", translator.contains("somekey"));

		translator.set("somekey", null);
		Assert.assertTrue("Contains returned wrong result for an existing key.", translator.contains("somekey"));

		translator.set("somekey2", "value");
		Assert.assertTrue("Contains returned wrong result for an existing key.", translator.contains("somekey2"));
	}

	@Test
	public void testDate() {
		Assert.assertFalse("Internal test case error: This key should not be set.", translator.contains("somekey"));

		final Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, -1);
		final Date value = calendar.getTime();

		translator.setDate("somekey", value);
		Assert.assertTrue("SetDate had not set the value correctly.", translator.contains("somekey"));

		final Date value2 = translator.getDate("somekey");
		Assert.assertEquals("GetDate returned invalid value.", value, value2);
	}

	@Test
	public void testDateFailing() {
		Assert.assertFalse("Internal test case error: This key should not be set.", translator.contains("somekey"));

		final Date value = null;

		translator.setDate("somekey", value);
		Assert.assertTrue("SetDate had not set the value correctly.", translator.contains("somekey"));

		final Date value2 = translator.getDate("somekey");
		Assert.assertNull("GetDate returned invalid value.", value2);
	}

	@Test
	public void testDouble() {
		Assert.assertFalse("Internal test case error: This key should not be set.", translator.contains("somekey"));

		final Double value = 4711.0D;

		translator.setDouble("somekey", value);
		Assert.assertTrue("SetDouble had not set the value correctly.", translator.contains("somekey"));

		final Double value2 = translator.getDouble("somekey");
		Assert.assertEquals("getDouble returned invalid value.", value, value2);
	}

	@Test
	public void testDoubleFailing() {
		Assert.assertFalse("Internal test case error: This key should not be set.", translator.contains("somekey"));

		final Double value = null;

		translator.setDouble("somekey", value);
		Assert.assertTrue("SetDouble had not set the value correctly.", translator.contains("somekey"));

		final Double value2 = translator.getDouble("somekey");
		Assert.assertNull("getDouble returned invalid value.", value2);
	}

	@Test
	public void testFloat() {
		Assert.assertFalse("Internal test case error: This key should not be set.", translator.contains("somekey"));

		final Float value = 4711.0F;

		translator.setFloat("somekey", value);
		Assert.assertTrue("SetFloat had not set the value correctly.", translator.contains("somekey"));

		final Float value2 = translator.getFloat("somekey");
		Assert.assertEquals("getFloat returned invalid value.", value, value2);
	}

	@Test
	public void testFloatFailing() {
		Assert.assertFalse("Internal test case error: This key should not be set.", translator.contains("somekey"));

		final Float value = null;

		translator.setFloat("somekey", value);
		Assert.assertTrue("SetFloat had not set the value correctly.", translator.contains("somekey"));

		final Float value2 = translator.getFloat("somekey");
		Assert.assertNull("getFloat returned invalid value.", value2);
	}

	@Test
	public void testInteger() {
		Assert.assertFalse("Internal test case error: This key should not be set.", translator.contains("somekey"));

		final Integer value = 4711;

		translator.setInteger("somekey", value);
		Assert.assertTrue("SetInteger had not set the value correctly.", translator.contains("somekey"));

		final Integer value2 = translator.getInteger("somekey");
		Assert.assertEquals("getInteger returned invalid value.", value, value2);
	}

	@Test
	public void testIntegerFailing() {
		Assert.assertFalse("Internal test case error: This key should not be set.", translator.contains("somekey"));

		final Integer value = null;

		translator.setInteger("somekey", value);
		Assert.assertTrue("SetInteger had not set the value correctly.", translator.contains("somekey"));

		final Integer value2 = translator.getInteger("somekey");
		Assert.assertNull("getInteger returned invalid value.", value2);
	}

	@Test
	public void testLong() {
		Assert.assertFalse("Internal test case error: This key should not be set.", translator.contains("somekey"));

		final Long value = 4711L;

		translator.setLong("somekey", value);
		Assert.assertTrue("SetLong had not set the value correctly.", translator.contains("somekey"));

		final Long value2 = translator.getLong("somekey");
		Assert.assertEquals("GetLong returned invalid value.", value, value2);
	}

	@Test
	public void testLongFailing() {
		Assert.assertFalse("Internal test case error: This key should not be set.", translator.contains("somekey"));

		final Long value = null;

		translator.setLong("somekey", value);
		Assert.assertTrue("SetLong had not set the value correctly.", translator.contains("somekey"));

		final Long value2 = translator.getLong("somekey");
		Assert.assertNull("GetLong returned invalid value.", value2);
	}

	@Test
	public void testString() {
		Assert.assertFalse("Internal test case error: This key should not be set.", translator.contains("somekey"));

		final String value = "mastervalue";

		translator.setString("somekey", value);
		Assert.assertTrue("setString had not set the value correctly.", translator.contains("somekey"));

		final String value2 = translator.getString("somekey");
		Assert.assertEquals("GetString returned invalid value.", value, value2);
	}

	@Test
	public void testStringFailing() {
		Assert.assertFalse("Internal test case error: This key should not be set.", translator.contains("somekey"));

		final String value = null;

		translator.setString("somekey", value);
		Assert.assertTrue("setString had not set the value correctly.", translator.contains("somekey"));

		final String value2 = translator.getString("somekey");
		Assert.assertNull("GetString returned invalid value.", value2);
	}

}
