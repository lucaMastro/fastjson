package param_tests;

import org.junit.Assert;
import junit.framework.TestCase;

import com.alibaba.fastjson.serializer.SerializeWriter;
import org.junit.Test;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class SerializeWriterTest extends TestCase {
	/* test 0 params */
	private char[] chars_to_append;
	private Integer[] ints_to_append;
	private Long[] longs_to_append;
	private String[] first_output_strings;
	private String[] second_output_strings;

	private SerializeWriter writer;
	private final int max_test_index = 6;
	

	public SerializeWriterTest() throws IOException {
		int len = this.max_test_index + 1;
		this.chars_to_append = new char[len];
		this.ints_to_append = new Integer[len];
		this.longs_to_append = new Long[len];
		this.first_output_strings = new String[len];
		this.second_output_strings = new String[len];
		this.configure();
	}

	public void configure() throws IOException {
		for (int i = 0; i <= this.max_test_index; i++) {
			try{
				this.chars_to_append[i] = Support.getProperty("SerializeWriterTest_test_" + i + "_char").toCharArray()[0];
			}catch (Exception e){}
			try{
				this.ints_to_append[i] = Integer.valueOf(Support.getProperty("SerializeWriterTest_test_" + i + "_int"));
			}catch (Exception e){}
			try{
				String str = Support.getProperty("SerializeWriterTest_test_" + i + "_long");
				this.longs_to_append[i] = Long.valueOf(Support.stripTrailingL(str));
			}catch (Exception e){}
			try{
				this.first_output_strings[i] = Support.getProperty("SerializeWriterTest_test_" + i + "_first_output");
			}catch (Exception e){}
			try{
				this.second_output_strings[i] = Support.getProperty("SerializeWriterTest_test_" + i + "_second_output");
			}catch (Exception e){}
		}
		this.writer = new SerializeWriter();
	}

	@Test
	public void test_0() throws Exception {
		//SerializeWriter writer = new SerializeWriter();
		writer.append(this.chars_to_append[0]);
		writer.writeInt(this.ints_to_append[0]);
		Assert.assertEquals(this.first_output_strings[0], writer.toString());
		writer.writeLong(this.longs_to_append[0]);
		Assert.assertEquals(this.second_output_strings[0], writer.toString());

	}

	@Test
	public void test_1() throws Exception {
		//SerializeWriter writer = new SerializeWriter();
		writer.writeInt(this.ints_to_append[1]);
		Assert.assertEquals(this.first_output_strings[1], writer.toString());
	}

	@Test
	public void test_4() throws Exception {
		//SerializeWriter writer = new SerializeWriter();
		writer.writeInt(this.ints_to_append[4]);
		writer.write(this.chars_to_append[4]);
		Assert.assertEquals(this.first_output_strings[4], writer.toString());
	}

	@Test
	public void test_5() throws Exception {
		//SerializeWriter writer = new SerializeWriter();
		writer.writeLong(this.longs_to_append[5]);
		Assert.assertEquals(this.first_output_strings[5], writer.toString());
	}

	@Test
	public void test_6() throws Exception {
		//SerializeWriter writer = new SerializeWriter();
		writer.writeLong(this.longs_to_append[6]);
		writer.write(this.chars_to_append[6]);
		Assert.assertEquals(this.first_output_strings[6], writer.toString());
	}
}
