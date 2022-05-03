package param_tests;

import org.junit.Assert;
import junit.framework.TestCase;

import com.alibaba.fastjson.serializer.SerializeWriter;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Enclosed.class)
public class SerializeWriterTest extends TestCase {

	@RunWith(Parameterized.class)
	public static class Test_0 {
		private char aChar;
		private int anInt;
		private long aLong;
		private String first_out;
		private String second_out;

		private SerializeWriter writer;

		@Parameterized.Parameters
		public static Collection<Object[]> data() {
			return Arrays.asList(new Object[][] {
					{ 'A', 156, 345 }
			});
		}

		public Test_0(char aChar, int anInt, long aLong){
			this.writer = new SerializeWriter();
			this.configure(aChar, anInt, aLong);
		}

		public void configure(char aChar, int anInt, long aLong){
			this.aChar = aChar;
			this. anInt = anInt;
			this.aLong = aLong;

			StringBuilder outputs = new StringBuilder();
			outputs.append(aChar).append(anInt);
			this.first_out = outputs.toString();
			outputs.append(aLong);
			this.second_out = outputs.toString();
		}

		@Test
		public void test_0() throws Exception {
			//SerializeWriter writer = new SerializeWriter();
			writer.append(this.aChar);
			writer.writeInt(this.anInt);
			Assert.assertEquals(this.first_out, writer.toString());
			writer.writeLong(this.aLong);
			Assert.assertEquals(this.second_out, writer.toString());
		}
	}

	@RunWith(Parameterized.class)
	public static class Test_1 {
		private int anInt;
		private String first_out;

		private SerializeWriter writer;

		@Parameterized.Parameters
		public static Collection<Object[]> data() {
			return Arrays.asList(new Object[][] {
					{ -1 }
			});
		}

		public Test_1(int anInt){
			this.writer = new SerializeWriter();
			this.configure(anInt);
		}

		public void configure(int anInt){

			this. anInt = anInt;

			StringBuilder outputs = new StringBuilder();
			outputs.append(anInt);
			this.first_out = outputs.toString();
		}

		@Test
		public void test_1() throws Exception {
			//SerializeWriter writer = new SerializeWriter();
			writer.writeInt(this.anInt);
			Assert.assertEquals(this.first_out, writer.toString());
		}
	}

	@RunWith(Parameterized.class)
	public static class Test_4 {
		private char aChar;
		private int anInt;
		private String first_out;

		private SerializeWriter writer;

		@Parameterized.Parameters
		public static Collection<Object[]> data() {
			return Arrays.asList(new Object[][] {
					{ ',', 1 }
			});
		}

		public Test_4(char aChar, int anInt){
			this.writer = new SerializeWriter();
			this.configure(aChar, anInt);
		}

		public void configure(char aChar, int anInt){
			this.aChar = aChar;
			this. anInt = anInt;

			StringBuilder outputs = new StringBuilder();
			outputs.append(anInt).append(aChar);
			this.first_out = outputs.toString();
		}

		@Test
		public void test_4() throws Exception {
			//SerializeWriter writer = new SerializeWriter();
			writer.writeInt(this.anInt);
			writer.write(this.aChar);
			Assert.assertEquals(this.first_out, writer.toString());
		}
	}

	@RunWith(Parameterized.class)
	public static class Test_5 {
		private long aLong;
		private String first_out;

		private SerializeWriter writer;

		@Parameterized.Parameters
		public static Collection<Object[]> data() {
			return Arrays.asList(new Object[][] {
					{ -1L }
			});
		}

		public Test_5(long aLong){
			this.writer = new SerializeWriter();
			this.configure(aLong);
		}

		public void configure(long aLong){
			this.aLong = aLong;

			StringBuilder outputs = new StringBuilder();
			outputs.append(aLong);
			this.first_out = outputs.toString();
		}

		@Test
		public void test_5() throws Exception {
			//SerializeWriter writer = new SerializeWriter();
			writer.writeLong(this.aLong);
			Assert.assertEquals(this.first_out, writer.toString());
		}
	}

	@RunWith(Parameterized.class)
	public static class Test_6 {
		private char aChar;
		private long aLong;
		private String first_out;

		private SerializeWriter writer;

		@Parameterized.Parameters
		public static Collection<Object[]> data() {
			return Arrays.asList(new Object[][] {
					{ ',', -1L }
			});
		}

		public Test_6(char aChar, long aLong){
			this.writer = new SerializeWriter();
			this.configure(aChar, aLong);
		}

		public void configure(char aChar, long aLong){
			this.aChar = aChar;
			this.aLong = aLong;

			StringBuilder outputs = new StringBuilder();
			outputs.append(aLong);
			outputs.append(aChar);
			this.first_out = outputs.toString();
		}

		@Test
		public void test_6() throws Exception {
			//SerializeWriter writer = new SerializeWriter();
			writer.writeLong(this.aLong);
			writer.write(this.aChar);
			Assert.assertEquals(this.first_out, writer.toString());
		}
	}
}
