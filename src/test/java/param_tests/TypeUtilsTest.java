package param_tests;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.*;

import com.alibaba.fastjson.parser.ParserConfig;
import junit.framework.TestCase;

import org.junit.Assert;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.util.TypeUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


@SuppressWarnings("rawtypes")
@RunWith(Enclosed.class)
public class TypeUtilsTest extends TestCase {

    public static class Without_params{
        private HashMap hashMap;
        private JSONObject jsonMap;

        public Without_params(){
            this.hashMap = new HashMap();
            this.jsonMap = new JSONObject();
        }

        @Test
        public void test_0() throws Exception {
            //HashMap map = new HashMap();
            Assert.assertTrue(this.hashMap == TypeUtils.castToJavaBean(this.hashMap, Map.class));
        }

        @Test
        public void test_1() throws Exception {
            //JSONObject map = new JSONObject();
            Assert.assertTrue(jsonMap == TypeUtils.castToJavaBean(jsonMap, Map.class));
        }

        @Test
        public void test_cast_Array() throws Exception {
            Assert.assertEquals(Integer[].class, TypeUtils.cast(new ArrayList(), Integer[].class, null).getClass());
        }
    }

    @RunWith(Parameterized.class)
    public static class Test_2 {
        private JSONObject map;
        private String key1;
        private String key2;
        private int value1;
        private String value2;


        @Parameterized.Parameters
        public static Collection<Object[]> data() {
            return Arrays.asList(new Object[][]{
                    {"id", 1, "name", "panlei"}
            });
        }

        public Test_2(String key1, int value1, String key2, String value2) {
            this.map = new JSONObject();
            this.configure(key1, value1, key2, value2);
        }

        private void configure(String key1, int value1, String key2, String value2) {
            this.key1 = key1;
            this.key2 = key2;
            this.value1 = value1;
            this.value2 = value2;
        }

        @Test
        public void test_2() throws Exception {
            //JSONObject map = new JSONObject();
            map.put(this.key1, this.value1);
            map.put(this.key2, this.value2);

            User user = TypeUtils.castToJavaBean(map, User.class);
            Assert.assertEquals((long) this.value1, user.getId());
            Assert.assertEquals(this.value2, user.getName());
        }

        @Test
        public void test_3() throws Exception {
            //JSONObject map = new JSONObject();
            map.put(this.key1, this.value1);
            map.put(this.key2, this.value2);

            User user = JSON.toJavaObject(map, User.class);
            Assert.assertEquals((long) this.value1, user.getId());
            Assert.assertEquals(this.value2, user.getName());
        }
    }

    @RunWith(Parameterized.class)
    public static class Test_cast_integer {
        private JSONObject json;
        private String key;
        private long value;


        @Parameterized.Parameters
        public static Collection<Object[]> data() {
            return Arrays.asList(new Object[][]{
                    {"id", 1L}
            });
        }

        public Test_cast_integer(String key, long value) {
            this.json = new JSONObject();
            this.configure(key, value);
        }

        private void configure(String key, long value) {
            this.key = key;
            this.value = value;
        }

        @Test
        public void test_cast_Integer() throws Exception {
            //JSONObject json = new JSONObject();
            json.put(this.key, this.value);
            Assert.assertEquals(new Integer((int) this.value), json.getObject(this.key, int.class));
        }

        @Test
        public void test_cast_Integer_2() throws Exception {
            //JSONObject json = new JSONObject();
            json.put(this.key, this.value);
            Assert.assertEquals(new Integer((int) this.value), json.getObject(this.key, Integer.class));
        }
    }

    @RunWith(Parameterized.class)
    public static class Test_cast_int_to_multiple_types {
        private JSONObject json;
        private String key;
        private long value;


        @Parameterized.Parameters
        public static Collection<Object[]> data() {
            return Arrays.asList(new Object[][]{
                    {"id", 1}
            });
        }

        public Test_cast_int_to_multiple_types(String key, int value) {
            this.json = new JSONObject();
            this.configure(key, value);
        }

        private void configure(String key, int value) {
            this.key = key;
            this.value = value;
        }

        @Test
        public void test_cast_to_long() throws Exception {
            //JSONObject json = new JSONObject();
            json.put(this.key, this.value);
            Assert.assertEquals(new Long(this.value), json.getObject(this.key, long.class));
        }

        @Test
        public void test_cast_to_Long() throws Exception {
            //JSONObject json = new JSONObject();
            json.put(this.key, 1);
            Assert.assertEquals(new Long(this.value), json.getObject(this.key, Long.class));
        }

        @Test
        public void test_cast_to_short() throws Exception {
            //JSONObject json = new JSONObject();
            json.put(this.key, this.value);
            Assert.assertEquals(new Short((short) this.value), json.getObject(this.key, short.class));
        }

        @Test
        public void test_cast_to_Short() throws Exception {
            //JSONObject json = new JSONObject();
            json.put(this.key, 1);
            Assert.assertEquals(new Short((short) this.value), json.getObject(this.key, Short.class));
        }
        @Test
        public void test_cast_to_byte() throws Exception {
            //JSONObject json = new JSONObject();
            json.put(this.key, this.value);
            Assert.assertEquals(new Byte((byte) this.value), json.getObject(this.key, byte.class));
        }

        @Test
        public void test_cast_to_Byte() throws Exception {
            //JSONObject json = new JSONObject();
            json.put(this.key, 1);
            Assert.assertEquals(new Byte((byte) this.value), json.getObject(this.key, Byte.class));
        }

        @Test
        public void test_cast_to_BigInteger() throws Exception {
            //JSONObject json = new JSONObject();
            json.put(this.key, this.value);
            Assert.assertEquals(new BigInteger(String.valueOf(this.value)), json.getObject(this.key, BigInteger.class));
        }

        @Test
        public void test_cast_to_BigDecimal() throws Exception {
            //JSONObject json = new JSONObject();
            json.put(this.key, this.value);
            Assert.assertEquals(new BigDecimal(String.valueOf(this.value)), json.getObject(this.key, BigDecimal.class));
        }

        @Test
        public void test_cast_to_boolean() throws Exception {
            //JSONObject json = new JSONObject();
            json.put(this.key, this.value);
            Assert.assertEquals(Boolean.TRUE, json.getObject(this.key, boolean.class));
        }

        @Test
        public void test_cast_to_Boolean() throws Exception {
            //JSONObject json = new JSONObject();
            json.put(this.key, this.value);
            Assert.assertEquals(Boolean.TRUE, json.getObject(this.key, Boolean.class));
        }

        @Test
        public void test_cast_null() throws Exception {
            //JSONObject json = new JSONObject();
            json.put(this.key, null);
            Assert.assertEquals(null, json.getObject(this.key, Boolean.class));
        }

        @Test
        public void test_cast_to_String() throws Exception {
            //JSONObject json = new JSONObject();
            json.put(this.key, this.value);
            Assert.assertEquals(String.valueOf(this.value), json.getObject(this.key, String.class));
        }
    }

    @RunWith(Parameterized.class)
    public static class Test_cast_date {
        private JSONObject json;
        private String key;
        private long millis;
        private String timeZone;


        @Parameterized.Parameters
        public static Collection<Object[]> data() {
            return Arrays.asList(new Object[][]{
                    {"date", System.currentTimeMillis(), "Asia/Shanghai"}
            });
        }

        public Test_cast_date(String key, long value, String timeZone) {
            this.json = new JSONObject();
            this.configure(key, value, timeZone);
        }

        private void configure(String key, long value, String timeZone) {
            this.key = key;
            this.millis = value;
            this.timeZone = timeZone;
        }

        @Test
        public void test_cast_to_Date() throws Exception {
            //long millis = System.currentTimeMillis();

            //JSONObject json = new JSONObject();
            json.put(this.key, this.millis);
            Assert.assertEquals(new Date(this.millis), json.getObject(this.key, Date.class));
        }

        @Test
        public void test_cast_to_SqlDate() throws Exception {
            //long millis = System.currentTimeMillis();

            //JSONObject json = new JSONObject();
            json.put(this.key, this.millis);
            Assert.assertEquals(new java.sql.Date(this.millis), json.getObject(this.key, java.sql.Date.class));
        }

        @Test
        public void test_cast_to_SqlDate_string() throws Exception {
            //long millis = System.currentTimeMillis();

            //JSONObject json = new JSONObject();
            json.put(this.key, Long.toString(this.millis));
            Assert.assertEquals(new java.sql.Date(this.millis), json.getObject(this.key, java.sql.Date.class));
        }

        @Test
        public void test_cast_to_SqlDate_null() throws Exception {
            //JSONObject json = new JSONObject();
            json.put(this.key, null);
            Assert.assertEquals(null, json.getObject(this.key, java.sql.Date.class));
        }

        @Test
        public void test_cast_to_SqlDate_null2() throws Exception {
            Assert.assertEquals(null, TypeUtils.castToSqlDate(null));
        }

        @Test
        public void test_cast_to_SqlDate_util_Date() throws Exception {
            //long millis = System.currentTimeMillis();

            //JSONObject json = new JSONObject();
            json.put(this.key, new Date(this.millis));
            Assert.assertEquals(new java.sql.Date(this.millis), json.getObject(this.key, java.sql.Date.class));
        }

        @Test
        public void test_cast_to_SqlDate_sql_Date() throws Exception {
            //long millis = System.currentTimeMillis();

            //JSONObject json = new JSONObject();
            json.put(this.key, new java.sql.Date(millis));
            Assert.assertEquals(new java.sql.Date(millis), json.getObject(this.key, java.sql.Date.class));
        }

        @Test
        public void test_cast_to_SqlDate_sql_Date2() throws Exception {
            //long millis = System.currentTimeMillis();

            java.sql.Date date = new java.sql.Date(millis);
            Assert.assertEquals(date, TypeUtils.castToSqlDate(date));
        }

        @Test
        public void test_cast_to_SqlDate_calendar() throws Exception {
            //long millis = System.currentTimeMillis();

            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(millis);

            //JSONObject json = new JSONObject();
            json.put(key, calendar);
            Assert.assertEquals(new java.sql.Date(millis), json.getObject(this.key, java.sql.Date.class));
        }

        @Test
        public void test_cast_to_SqlDate_error() throws Exception {
            JSONObject json = new JSONObject();
            json.put(this.key, 0);

            JSONException error = null;
            try {
                json.getObject(this.key, java.sql.Date.class);
            } catch (JSONException e) {
                error = e;
            }
            Assert.assertNotNull(error);
        }

        @Test
        public void test_cast_to_Timestamp() throws Exception {
            //long millis = System.currentTimeMillis();

            //JSONObject json = new JSONObject();
            json.put(this.key, millis);
            Assert.assertEquals(new java.sql.Timestamp(millis), json.getObject(this.key, java.sql.Timestamp.class));
        }

        @Test
        public void test_cast_to_Timestamp_string() throws Exception {
            //long millis = System.currentTimeMillis();

            //JSONObject json = new JSONObject();
            json.put(this.key, Long.toString(millis));
            Assert.assertEquals(new java.sql.Timestamp(millis), json.getObject(this.key, java.sql.Timestamp.class));
        }
        @Test
        public void test_cast_to_Timestamp_number() throws Exception {
            //long millis = System.currentTimeMillis();

            //JSONObject json = new JSONObject();
            json.put(this.key, new BigDecimal(Long.toString(millis)));
            Assert.assertEquals(new java.sql.Timestamp(millis), json.getObject(this.key, java.sql.Timestamp.class));
        }

        @Test
        public void test_cast_to_Timestamp_null() throws Exception {
            //JSONObject json = new JSONObject();
            json.put(this.key, null);
            Assert.assertEquals(null, json.getObject(this.key, java.sql.Timestamp.class));
        }

        @Test
        public void test_cast_to_Timestamp_null2() throws Exception {
            Assert.assertEquals(null, TypeUtils.castToTimestamp(null));
        }

        @Test
        public void test_cast_to_Timestamp_1970_01_01_00_00_00() throws Exception {
            JSON.defaultTimeZone = TimeZone.getTimeZone(this.timeZone);
            Assert.assertEquals(new Timestamp(0), TypeUtils.castToTimestamp("1970-01-01 08:00:00"));
        }

        @Test
        public void test_cast_to_Timestamp_util_Date() throws Exception {
            //long millis = System.currentTimeMillis();

            //JSONObject json = new JSONObject();
            json.put(this.key, new Date(millis));
            Assert.assertEquals(new java.sql.Timestamp(millis), json.getObject(this.key, java.sql.Timestamp.class));
        }

        @Test
        public void test_cast_to_Timestamp_sql_Date() throws Exception {
            //long millis = System.currentTimeMillis();

            //JSONObject json = new JSONObject();
            json.put(this.key, new java.sql.Date(millis));
            Assert.assertEquals(new java.sql.Timestamp(millis), json.getObject(this.key, java.sql.Timestamp.class));
        }

        @Test
        public void test_cast_to_Timestamp_sql_Timestamp() throws Exception {
            //long millis = System.currentTimeMillis();

            java.sql.Timestamp date = new java.sql.Timestamp(millis);
            Assert.assertEquals(date, TypeUtils.castToTimestamp(date));
        }

        @Test
        public void test_cast_to_Timestamp_calendar() throws Exception {
            //long millis = System.currentTimeMillis();

            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(millis);

            //JSONObject json = new JSONObject();
            json.put(this.key, calendar);
            Assert.assertEquals(new java.sql.Timestamp(millis), json.getObject(this.key, java.sql.Timestamp.class));
        }

        @Test
        @Ignore
        public void test_cast_to_Timestamp_not_error() throws Exception {
            JSONObject json = new JSONObject();
            json.put("date", -1);

            JSONException error = null;
            try {
                json.getObject("date", java.sql.Timestamp.class);
            } catch (JSONException e) {
                error = e;
            }
            Assert.assertNull(error);
            Assert.assertEquals(new Timestamp(-1L), (java.sql.Timestamp) json.getObject("date", java.sql.Timestamp.class));
        }


    }

    @RunWith(Parameterized.class)
    public static class Test_cast_big_decimal {
        private JSONObject json;;
        private String str_value;


        @Parameterized.Parameters
        public static Collection<Object[]> data() {
            return Arrays.asList(new Object[][]{
                    {"123" }
            });
        }

        public Test_cast_big_decimal(String value) {
            this.json = new JSONObject();
            this.configure(value);
        }

        private void configure(String value) {
            this.str_value = value;
        }

        @Test
        public void test_cast_to_BigDecimal_same() throws Exception {
            BigDecimal value = new BigDecimal(this.str_value);
            Assert.assertEquals(true, value == TypeUtils.castToBigDecimal(value));
        }

        @Test
        public void test_cast_to_BigInteger_same() throws Exception {
            BigInteger value = new BigInteger(this.str_value);
            Assert.assertEquals(true, value == TypeUtils.castToBigInteger(value));
        }
    }


    @RunWith(Parameterized.class)
    public static class Test_cast_ab {
        private JSONObject json;
        private String key;
        private A a;
        private B b;


        @Parameterized.Parameters
        public static Collection<Object[]> data() {
            return Arrays.asList(new Object[][]{
                    {"value"}
            });
        }

        public Test_cast_ab(String key) {
            this.json = new JSONObject();
            this.configure(key);
        }

        private void configure(String key) {
            this.key = key;
            this.a = new A();
            this.b = new B();
        }

        @Test
        public void test_cast_ab() throws Exception {
            //B b = new B();

            //JSONObject json = new JSONObject();
            json.put(this.key, b);
            Assert.assertEquals(b, json.getObject(this.key, A.class));
        }

        @Test
        public void test_cast_ab_1() throws Exception {
            //B b = new B();

            //JSONObject json = new JSONObject();
            json.put(this.key, b);
            Assert.assertEquals(b, json.getObject(this.key, IA.class));
        }

        @Test
        public void test_cast_ab_error() throws Exception {
            //A a = new A();

            //JSONObject json = new JSONObject();
            json.put(this.key, a);

            JSONException error = null;
            try {
                json.getObject(this.key, B.class);
            } catch (JSONException e) {
                error = e;
            }
            Assert.assertNotNull(error);
        }
    }


    @RunWith(Parameterized.class)
    public static class Test_cast_errors {
        private JSONObject json;
        private String key;
        private int value;
        private String methodName;


        @Parameterized.Parameters
        public static Collection<Object[]> data() {
            return Arrays.asList(new Object[][]{
                    {"id", 1, "f"}
            });
        }

        public Test_cast_errors(String key, int value, String methodName) {
            this.json = new JSONObject();
            this.configure(key, value, methodName);
        }

        private void configure(String key, int value, String methodName) {
            this.key = key;
            this.value = value;
            this.methodName = methodName;
        }

        @Test
        public void test_error() throws Exception {
            //JSONObject json = new JSONObject();
            json.put(this.key, this.value);

            JSONException error = null;
            try {
                TypeUtils.castToJavaBean(json, C.class, ParserConfig.getGlobalInstance());
            } catch (JSONException e) {
                error = e;
            }
            Assert.assertNotNull(error);
        }

        @Test
        public void test_error_2() throws Exception {
            //JSONObject json = new JSONObject();
            json.put(this.key, this.value);

            Method method = TypeUtilsTest.class.getMethod(this.methodName, List.class);

            Throwable error = null;
            try {
                TypeUtils.cast(json, method.getGenericParameterTypes()[0], ParserConfig.getGlobalInstance());
            } catch (JSONException ex) {
                error = ex;
            }
            assertNotNull(error);
        }
    }



    @Ignore
    public static class User {

        private long id;
        private String name;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    @Ignore
    public static class A implements IA {

    }

    @Ignore
    public static interface IA {

    }

    @Ignore
    public static class B extends A {

    }

    @Ignore
    public static class C extends B {

        public int getId() {
            throw new UnsupportedOperationException();
        }

        public void setId(int id) {
            throw new UnsupportedOperationException();
        }
    }

    public static void f(List<?> list) {

    }
}
