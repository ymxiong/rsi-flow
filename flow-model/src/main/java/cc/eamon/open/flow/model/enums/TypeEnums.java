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

public enum TypeEnums {

    /**
     * 基础数据类型及其包装类
     */
    VOID("void", void.class),
    BOOLEAN("boolean", boolean.class),
    BYTE("byte", byte.class),
    SHORT("short", short.class),
    INT("int", int.class),
    LONG("long", long.class),
    CHAR("char", char.class),
    FLOAT("float", float.class),
    DOUBLE("double", double.class),
    BOXED_VOID("Void", Void.class),
    BOXED_BOOLEAN("Boolean", Boolean.class),
    BOXED_BYTE("Byte", Byte.class),
    BOXED_SHORT("Short", Short.class),
    BOXED_INT("Integer", Integer.class),
    BOXED_LONG("Long", Long.class),
    BOXED_CHAR("Character", Character.class),
    BOXED_FLOAT("Float", Float.class),
    BOXED_DOUBLE("Double", Double.class),
    STRING("String", String.class),
    OBJECT("Object", Object.class),

    /**
     * java.io常用类
     */
    FILE("File", File.class),
    BUFFEREDREADER("BufferedReader", BufferedReader.class),
    BUFFEREDWRITER("BufferedWriter", BufferedWriter.class),
    DATAINPUTSTREAM("DataInputStream", DataInputStream.class),
    FILEINPUTSTREAM("FileInputStream", FileInputStream.class),
    FILEOUTPUTSTREAM("FileOutputStream", FileOutputStream.class),
    FILEREADER("FileReader", FileReader.class),
    FILEWRITER("FileWriter", FileWriter.class),
    INPUTSTREAM("InputStream", InputStream.class),
    OBJECTINPUTSTREAM("ObjectInputStream", ObjectInputStream.class),
    OBJECTOUTPUTSTREAM("ObjectOutputStream", ObjectOutputStream.class),
    READER("Reader", Reader.class),
    SERIALIZABLE("Serializable", Serializable.class),
    STRINGWRITER("StringWriter", StringWriter.class),
    WRITER("Writer", Writer.class),

    /**
     * java.net常用类
     */
    INETADDRESS("InetAddress", InetAddress.class),
    URL("URL", URL.class),
    URLCONNECTION("URLConnection", URLConnection.class),
    SOCKET("Socket", Socket.class),
    SERVERSOCKET("ServerSocket", ServerSocket.class),
    DATAGRAMPACKET("DatagramPacket", DatagramPacket.class),
    DATAGRAMSOCKET("DatagramSocket", DatagramSocket.class),
    MULTICASTSOCKET("MulticastSocket", MulticastSocket.class),

    /**
     * java.lang常用类
     */
    MATH("Math", Math.class),
    STRINGBUFFER("StringBuffer", StringBuffer.class),
    STRINGBUILDER("StringBuilder", StringBuilder.class),
    THREAD("Thread", Thread.class),
    CLASS("class", Class.class),
    CLASSLOADER("ClassLoader", ClassLoader.class),


    /**
     * java.math常用类
     */
    BIGDECIMAL("BigDecimal", BigDecimal.class),
    BIGINTEGER("BigInteger", BigInteger.class),


    /**
     * java.util常用类
     */
    COLLECTION("Collection", Collection.class),
    LIST("List", List.class),
    SET("Set", Set.class),
    MAP("Map", Map.class),
    ARRAYLIST("ArrayList", ArrayList.class),
    LINKEDLIST("LinkedList", LinkedList.class),
    VECTOR("Vector", Vector.class),
    QUEUE("Queue", Queue.class),
    HASHMAP("HashMap", HashMap.class),
    TREEMAP("TreeMap", TreeMap.class),
    LINKEDHASHMAP("HashMap", LinkedHashMap.class),
    HASHTABLE("Hashtable", Hashtable.class),
    ARRAYS("Arrays", Arrays.class),
    DATE("Date", java.util.Date.class),
    DATETIME("DateTime", java.lang.Long.class),
    CALENDAR("Calendar", Calendar.class),
    UUID("UUID", UUID.class),
    CONDITION("Condition", java.util.concurrent.locks.Condition.class),
    COUNTDOWNLATCH("CountDownLatch", CountDownLatch.class),
    CYCLICBARRIER("CyclicBarrier", CyclicBarrier.class),
    SEMAPHORE("Semaphore", Semaphore.class),
    REENTRANTLOCK("ReentrantLock", java.util.concurrent.locks.ReentrantLock.class),
    REENTRANTREADWRITELOCK("ReentrantReadWriteLock", java.util.concurrent.locks.ReentrantReadWriteLock.class),


    /**
     * java sql包
     */
    DRIVERMANAGER("DriverManager", DriverManager.class),
    CONNECTION("Connection", Connection.class),
    STATEMENT("Statement", Statement.class),
    PREPAREDSTATEMENT("PreparedStatement", PreparedStatement.class),
    CALLABLESTATEMENT("CallableStatement", CallableStatement.class),
    DATABASEMETADATA("DatabaseMetaData", DatabaseMetaData.class),
    RESULTSET("ResultSet", ResultSet.class),

    ;

    private final String keyword;

    private final Class<?> classType;

    private Object defaultValue = null;

    TypeEnums(String keyword, Class<?> classType) {
        this.keyword = keyword;
        this.classType = classType;
    }

    public static TypeEnums getTypeByKeyword(String keyword) {
        keyword = keyword.split("\\(")[0];
        for (TypeEnums value : TypeEnums.values()) {
            if (value.getKeyword().equals(keyword)) return value;
        }
        return TypeEnums.OBJECT;
//        throw new FlowException("不支持的数据类型");
    }

    public static TypeEnums getTypeByClassDefaultObject(Class<?> classType) {
        for (TypeEnums value : TypeEnums.values()) {
            if (classType != null && classType == value.getClassType()) return value;
        }
        return TypeEnums.OBJECT;
//        throw new FlowException("不支持的数据类型");
    }

    public static Class<?> getType(String reference) {
        Class<?> clazz = null;
        if (!reference.contains(".")) {
            clazz = getPreparedType(reference);
        } else {
            clazz = getAdditionalType(reference);
        }
        return clazz;
    }

    private static Class<?> getPreparedType(String keyword) {
        TypeEnums preparedTypeEnum = getPreparedTypeEnum(keyword);
        return preparedTypeEnum == null ? null : preparedTypeEnum.classType;
    }

    private static Class<?> getAdditionalType(String reference) {
        Class<?> clazz = null;
        try {
            clazz = Class.forName(reference);
        } catch (ClassNotFoundException e) {
            Logger logger = LoggerFactory.getLogger(TypeEnums.class);
            logger.info("Class<?> named " + e.getMessage() + " basic type not found.");
        }
        return clazz;
    }

    private static TypeEnums getPreparedTypeEnum(String keyword) {
        for (TypeEnums value : TypeEnums.values()) {
            if (value.getKeyword().equals(keyword)) return value;
        }
        return null;
    }

    public static TypeEnums getTypeByClass(Class<?> classType) {
        for (TypeEnums value : TypeEnums.values()) {
            if (classType != null && classType == value.getClassType()) return value;
        }
        return null;
    }

    public static BasicType getTypeByReference(String reference) {
        TypeEnums enumType = getPreparedTypeEnum(reference);
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
        Package p = getClassType().getPackage();
        return p == null ? "" : p.getName();
    }

    public Class<?> getClassType() {
        return classType;
    }

    public Object getDefaultValue() {
        return defaultValue;
    }

    public static class BasicType {

        private String keyword;

        private String packageName;

        private Class<?> classType;

        public BasicType() {
        }

        public BasicType(TypeEnums enums) {
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

