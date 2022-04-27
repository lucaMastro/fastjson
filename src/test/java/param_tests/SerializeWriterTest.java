package param_tests;

import org.junit.Assert;
import junit.framework.TestCase;

import com.alibaba.fastjson.serializer.SerializeWriter;
import org.junit.Test;

public class SerializeWriterTest extends TestCase {
	/* test 0 params */
	private char char_to_append;
	private Integer int_to_append;
	private Long long_to_append;
	private String first_output_string;
	private String second_output_string;

	public SerializeWriterTest(){
		this.configure();
	}


	public void configure(){
		this.char_to_append = 'A';
		this.int_to_append = 156;
		this.long_to_append = Long.valueOf(345);
		this.first_output_string = new StringBuilder(this.char_to_append).
				append(this.int_to_append).toString();
		this.second_output_string = new StringBuilder(this.first_output_string).
				append(this.long_to_append).toString();
	}

	@Test
	public void test_0() throws Exception {
		SerializeWriter writer = new SerializeWriter();
		writer.append('A');
		writer.writeInt(156);
		Assert.assertEquals("A156", writer.toString());
		writer.writeLong(345);
		Assert.assertEquals("A156345", writer.toString());

	}

	@Test
	public void test_1() throws Exception {
		SerializeWriter writer = new SerializeWriter();
		writer.writeInt(-1);
		Assert.assertEquals("-1", writer.toString());
	}

	@Test
	public void test_4() throws Exception {
		SerializeWriter writer = new SerializeWriter();
		writer.writeInt(-1);
		writer.write(',');
		Assert.assertEquals("-1,", writer.toString());
	}

	@Test
	public void test_5() throws Exception {
		SerializeWriter writer = new SerializeWriter();
		writer.writeLong(-1L);
		Assert.assertEquals("-1", writer.toString());
	}

	@Test
	public void test_6() throws Exception {
		SerializeWriter writer = new SerializeWriter();
		writer.writeLong(-1L);
		writer.write(',');
		Assert.assertEquals("-1,", writer.toString());
	}
}
