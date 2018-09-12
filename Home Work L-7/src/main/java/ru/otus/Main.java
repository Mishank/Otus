package ru.otus;
import javax.management.*;
import java.lang.management.ManagementFactory;
/*
 -agentlib:jdwp=transport=dt_socket,address=14000,server=y,suspend=n
 -Xms112m
 -Xmx112m
 -XX:MaxMetaspaceSize=256m
 -XX:+CMSParallelRemarkEnabled
 -XX:+UseCMSInitiatingOccupancyOnly
 -XX:CMSInitiatingOccupancyFraction=70
 -XX:+ScavengeBeforeFullGC
 -XX:+CMSScavengeBeforeRemark
 -verbose:gc
 -Xloggc:./logs/gc_pid_%p.log
 -Dcom.sun.management.jmxremote.port=15000
 -Dcom.sun.management.jmxremote.authenticate=false
 -Dcom.sun.management.jmxremote.ssl=false
 -XX:+HeapDumpOnOutOfMemoryError
 -XX:HeapDumpPath=./dumps/
 jps -- list vms or ps -e | grep java
 jstack <pid> >> threaddumps.log -- get dump from pid
 jinfo -- list VM parameters
 jhat / jvisualvm -- analyze heap dump
 */

public class Main {
    public static void main(String... args) throws Exception {
        System.out.println("Starting pid: " + ManagementFactory.getRuntimeMXBean().getName());

        //int size = 5 * 1000 * 1000;
        int size = 50 * 1000 * 1000;//for OOM with -Xms512m
        //int size = 50 * 1000 * 100; //for small dump

        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("ru.otus:type=Benchmark");
        Benchmark mbean = new Benchmark();
        mbs.registerMBean(mbean, name);

        mbean.setSize(size);
        mbean.run();
    }
}
