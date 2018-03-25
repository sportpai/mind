package com.mind.config;

import com.mind.config.entity.Node;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.management.*;
import java.text.NumberFormat;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@SpringBootApplication
public class ConfigApplication {
	static Map<String,Object> map = new HashMap(10000000);
	static BitSet bitSet = new BitSet(Integer.MAX_VALUE);

	public static void main(String[] args) {
		//SpringApplication.run(ConfigApplication.class, args);
		new Thread(() -> outInfo()).start();
		//addTest();
		testBitSet();
	}
	public static void testBitSet(){
		Random random = new Random();
		for(int i=0;i< 100;i++){
			int rand = random.nextInt(100);
			bitSet.set(rand ,true);
		}
		int cardinality = bitSet.cardinality();
		 int postion = bitSet.nextSetBit(1);

		System.out.println(bitSet.cardinality());

	}

	public static void addTest(){

		for(Long i=0L;i<10000000;i++){
			try {
				Node node = new Node();
				node.setId(i);
				map.put(i+"",node);
			}catch (Exception e){
				System.out.println(e);

			}
		}
	}

	public static void outInfo(){

		MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
		ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
		RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
		OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
		PlatformLoggingMXBean platformLoggingMXBean = ManagementFactory.getPlatformMXBean(PlatformLoggingMXBean.class);

		NumberFormat mumberFormat = NumberFormat.getInstance();
		while (true){
			try {

				Runtime run = Runtime.getRuntime();
				Long totalMemory = run.totalMemory();
				Long maxMemory = run.maxMemory();
				Long freeMemory = run.freeMemory();

				System.out.println("totalMemory:" + mumberFormat.format(totalMemory)+","
						+"maxMemory:" + mumberFormat.format(maxMemory) + ","
						+"freeMemory:" + mumberFormat.format(freeMemory) + ","
						+ "mapsize:" + mumberFormat.format(map.size()) + ","
						+ "bitSetLength:" + mumberFormat.format(bitSet.length()) + ","
				);

				MemoryUsage heapMemoryUsage = memoryMXBean.getHeapMemoryUsage();
				MemoryUsage nonHeapMemoryUsage = memoryMXBean.getNonHeapMemoryUsage();

				System.out.println(
					 "\n heapMemoryUsage.getMax:"+mumberFormat.format(heapMemoryUsage.getMax())
					+"\n heapMemoryUsage.getInit:"+mumberFormat.format(heapMemoryUsage.getInit())
					+"\n heapMemoryUsage.getUsed:"+mumberFormat.format(heapMemoryUsage.getUsed())
					+"\n heapMemoryUsage.getCommitted:"+mumberFormat.format(heapMemoryUsage.getCommitted())
				);
				System.out.println(
					 "\n nonHeapMemoryUsage.getMax:"+mumberFormat.format(nonHeapMemoryUsage.getMax())
					+"\n nonHeapMemoryUsage.getInit:"+mumberFormat.format(nonHeapMemoryUsage.getInit())
					+"\n nonHeapMemoryUsage.getUsed:"+mumberFormat.format(nonHeapMemoryUsage.getUsed())
					+"\n nonHeapMemoryUsage.getCommitted:"+mumberFormat.format(nonHeapMemoryUsage.getCommitted())
				);

				System.out.println("---------------------------------------------------------------------------------");

				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
