package com.yanci.flink.flinker;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.SourceFunction;

public class CheckPointMain {
	public static void main(String[] args) throws Exception {
		StreamExecutionEnvironment env = StreamExecutionEnvironment
				.getExecutionEnvironment();
		env.enableCheckpointing(10000);
		env.addSource(new SourceFunction<String>() {

			private static final long serialVersionUID = -2444503880266404078L;

			private volatile boolean isRunning;

			@Override
			public void run(SourceContext<String> ctx) throws Exception {
				isRunning = true;
				int a = 0;
				while (isRunning) {
					ctx.collect(String.valueOf(a));
					a++;
					Thread.sleep(5000);
				}
			}

			@Override
			public void cancel() {
				isRunning = false;
			}

		}).map(new MapFunction<String, String>() {

			/** 
			* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
			*/ 
			private static final long serialVersionUID = 3390161879834856459L;

			@Override
			public String map(String value) throws Exception {
				if("4".equals(value)) {
					throw new RuntimeException();
				}
				return value;
			}
		}).print();
		env.execute();
	}
}
