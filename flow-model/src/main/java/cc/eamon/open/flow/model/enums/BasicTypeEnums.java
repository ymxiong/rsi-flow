package cc.eamon.open.flow.model.enums;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.*;
import java.sql.*;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

/**
 * Author: lucas(jvjv)
 * Email: lucas@eamon.cc
 * Time: 2021/8/20 9:10
 */

public enum BasicTypeEnums {

    /**
     * 基础数据类型及其包装类
     */
    VOID("void", "", void.class),
    BOOLEAN("boolean", "", boolean.class),
    BYTE("byte", "", byte.class),
    SHORT("short", "", short.class),
    INT("int", "", int.class),
    LONG("long", "", long.class),
    CHAR("char", "", char.class),
    FLOAT("float", "", float.class),
    DOUBLE("double", "", double.class),
    BOXED_VOID("Void", "java.lang", Void.class),
    BOXED_BOOLEAN("Boolean", "java.lang", Boolean.class),
    BOXED_BYTE("Byte", "java.lang", Byte.class),
    BOXED_SHORT("Short", "java.lang", Short.class),
    BOXED_INT("Integer", "java.lang", Integer.class),
    BOXED_LONG("Long", "java.lang", Long.class),
    BOXED_CHAR("Character", "java.lang", Character.class),
    BOXED_FLOAT("Float", "java.lang", Float.class),
    BOXED_DOUBLE("Double", "java.lang", Double.class),
    STRING("String", "java.lang", String.class),
    OBJECT("Object", "java.lang", Object.class),

    /**
     * java.io常用类
     */
    FILE("File", "java.io", File.class),
    BUFFEREDREADER("BufferedReader", "java.io", BufferedReader.class),
    BUFFEREDWRITER("BufferedWriter", "java.io", BufferedWriter.class),
    DATAINPUTSTREAM("DataInputStream", "java.io", DataInputStream.class),
    FILEINPUTSTREAM("FileInputStream", "java.io", FileInputStream.class),
    FILEOUTPUTSTREAM("FileOutputStream", "java.io", FileOutputStream.class),
    FILEREADER("FileReader", "java.io", FileReader.class),
    FILEWRITER("FileWriter", "java.io", FileWriter.class),
    INPUTSTREAM("InputStream", "java.io", InputStream.class),
    OBJECTINPUTSTREAM("ObjectInputStream", "java.io", ObjectInputStream.class),
    OBJECTOUTPUTSTREAM("ObjectOutputStream", "java.io", ObjectOutputStream.class),
    READER("Reader", "java.io", Reader.class),
    SERIALIZABLE("Serializable", "java.io", Serializable.class),
    STRINGWRITER("StringWriter", "java.io", StringWriter.class),
    WRITER("Writer", "java.io", Writer.class),

    /**
     * java.net常用类
     */
    INETADDRESS("InetAddress", "java.net", InetAddress.class),
    URL("URL", "java.net", URL.class),
    URLCONNECTION("URLConnection", "java.net", URLConnection.class),
    SOCKET("Socket", "java.net", Socket.class),
    SERVERSOCKET("ServerSocket", "java.net", ServerSocket.class),
    DATAGRAMPACKET("DatagramPacket", "java.net", DatagramPacket.class),
    DATAGRAMSOCKET("DatagramSocket", "java.net", DatagramSocket.class),
    MULTICASTSOCKET("MulticastSocket", "java.net", MulticastSocket.class),

    /**
     * java.lang常用类
     */
    MATH("Math", "java.lang", Math.class),
    STRINGBUFFER("StringBuffer", "java.lang", StringBuffer.class),
    STRINGBUILDER("StringBuilder", "java.lang", StringBuilder.class),
    THREAD("Thread", "java.lang", Thread.class),
    CLASS("class", "java.lang", Class.class),
    CLASSLOADER("ClassLoader", "java.lang", ClassLoader.class),


    /**
     * java.math常用类
     */
    BIGDECIMAL("BigDecimal", "java.math", BigDecimal.class),
    BIGINTEGER("BigInteger", "java.math", BigInteger.class),


    /**
     * java.util常用类
     */
    COLLECTION("Collection", "java.util", Collection.class),
    LIST("List", "java.util", List.class),
    SET("Set", "java.util", Set.class),
    MAP("Map", "java.util", Map.class),
    ARRAYLIST("ArrayList", "java.util", ArrayList.class),
    LINKEDLIST("LinkedList", "java.util", LinkedList.class),
    VECTOR("Vector", "java.util", Vector.class),
    QUEUE("Queue", "java.util", Queue.class),
    HASHMAP("HashMap", "java.util", HashMap.class),
    TREEMAP("TreeMap", "java.util", TreeMap.class),
    LINKEDHASHMAP("HashMap", "java.util", LinkedHashMap.class),
    HASHTABLE("Hashtable", "java.util", Hashtable.class),
    ARRAYS("Arrays", "java.util", Arrays.class),
    DATE("Date", "java.util", java.util.Date.class),
    DATETIME("DateTime", "java.lang", java.lang.Long.class),
    CALENDAR("Calendar", "java.util", Calendar.class),
    UUID("UUID", "java.util", UUID.class),
    CONDITION("Condition", "java.util.concurrent.locks", java.util.concurrent.locks.Condition.class),
    COUNTDOWNLATCH("CountDownLatch", "java.util.concurrent", CountDownLatch.class),
    CYCLICBARRIER("CyclicBarrier", "java.util.concurrent", CyclicBarrier.class),
    SEMAPHORE("Semaphore", "java.util.concurrent", Semaphore.class),
    REENTRANTLOCK("ReentrantLock", "java.util.concurrent.locks", java.util.concurrent.locks.ReentrantLock.class),
    REENTRANTREADWRITELOCK("ReentrantReadWriteLock", "java.util.concurrent.locks", java.util.concurrent.locks.ReentrantReadWriteLock.class),


    /**
     * java sql包
     */
    DRIVERMANAGER("DriverManager", "java.sql", DriverManager.class),
    CONNECTION("Connection", "java.sql", Connection.class),
    STATEMENT("Statement", "java.sql", Statement.class),
    PREPAREDSTATEMENT("PreparedStatement", "java.sql", PreparedStatement.class),
    CALLABLESTATEMENT("CallableStatement", "java.sql", CallableStatement.class),
    DATABASEMETADATA("DatabaseMetaData", "java.sql", DatabaseMetaData.class),
    RESULTSET("ResultSet", "java.sql", ResultSet.class),

    ;

    private String keyword;

    private String packageName;

    private Class<?> classType;


    BasicTypeEnums(String keyword, String packageName, Class<?> classType) {
        this.keyword = keyword;
        this.packageName = packageName;
        this.classType = classType;
    }

    /**
     * 获取类型
     *
     * @param reference
     * @return
     */
    public static Class getType(String reference) {
        Class clazz = null;
        if (!reference.contains(".")) {
            clazz = getPreparedType(reference);
        } else {
            clazz = getAdditionalType(reference);
        }
        return clazz;
    }

    private static Class getPreparedType(String keyword) {
        BasicTypeEnums preparedTypeEnum = getPreparedTypeEnum(keyword);
        return preparedTypeEnum == null ? null : preparedTypeEnum.classType;
    }

    private static Class getAdditionalType(String reference) {
        Class clazz = null;
        try {
            clazz = Class.forName(reference);
        } catch (ClassNotFoundException e) {
            Logger logger = LoggerFactory.getLogger(BasicTypeEnums.class);
            logger.info("Class named " + e.getMessage() + " basic type not found.");
        }
        return clazz;
    }

    private static BasicTypeEnums getPreparedTypeEnum(String keyword) {
        for (BasicTypeEnums value : BasicTypeEnums.values()) {
            if (value.getKeyword().equals(keyword)) return value;
        }
        return null;
    }

    public static BasicTypeEnums getTypeByClass(Class<?> classType) {
        for (BasicTypeEnums value : BasicTypeEnums.values()) {
            if (classType != null && classType == value.getClassType()) return value;
        }
        return null;
    }

    public static BasicType getTypeByReference(String reference) {
        BasicTypeEnums enumType = getPreparedTypeEnum(reference);
        if (enumType != null) return new BasicType(enumType);

        if (!reference.contains(".")) return null;
        String[] values = reference.split("\\.");
        String keyword = values[values.length - 1];

        List<String> packages = new ArrayList<>();
        for (int i = 0; i < values.length - 1; i++) {
            packages.add(values[i]);
        }
        String packageName = String.join(".", packages);

        BasicType type = new BasicType();
        type.setKeyword(keyword);
        type.setPackageName(packageName);
        return type;
    }

    public String getKeyword() {
        return keyword;
    }

    public String getPackageName() {
        return packageName;
    }

    public Class<?> getClassType() {
        return classType;
    }

    public static class BasicType {

        private String keyword;

        private String packageName;

        private Class<?> classType;

        public BasicType() {
        }

        public BasicType(BasicTypeEnums enums) {
            this.keyword = enums.getKeyword();
            this.packageName = enums.getPackageName();
            this.classType = enums.getClassType();
        }

        public String getKeyword() {
            return keyword;
        }

        public void setKeyword(String keyword) {
            this.keyword = keyword;
        }

        public String getPackageName() {
            return packageName;
        }

        public void setPackageName(String packageName) {
            this.packageName = packageName;
        }

        public Class<?> getClassType() {
            return classType;
        }

        public void setClassType(Class<?> classType) {
            this.classType = classType;
        }


        public String getSimpleTypeName() {
            if (classType != null) return classType.getSimpleName();
            return keyword;
        }
    }

}

