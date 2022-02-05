package com.yanci.flink.flinker;

import java.io.IOException;
import java.io.Serializable;

import org.apache.flink.api.common.ExecutionConfig;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.io.FileInputFormat;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.FileProcessingMode;

public class TypeInfoTest {
	public static void main(String[] args) throws Exception {
		String fileName = "";

		StreamExecutionEnvironment env = StreamExecutionEnvironment
				.getExecutionEnvironment();
		env.readFile(new FileInputFormat<Abean>() {

			/**
			 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean reachedEnd() throws IOException {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public Abean nextRecord(Abean reuse) throws IOException {
				// TODO Auto-generated method stub
				return null;
			}
		}, fileName, FileProcessingMode.PROCESS_CONTINUOUSLY, 10,
				TypeInformation.of(Abean.class))
				.map(new MapFunction<Abean, Abean>() {

					/**
					 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
					 */
					private static final long serialVersionUID = 3898106324240336789L;

					@Override
					public Abean map(Abean value) throws Exception {
						// TODO Auto-generated method stub
						return null;
					}
				}).print();
		env.execute();
	}

	public static class Abean implements Serializable {

		/**
		 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
		 */
		private static final long serialVersionUID = -3005912309266933019L;
		private String a;

		public String getA() {
			return a;
		}

		public void setA(String a) {
			this.a = a;
		}

	}
}
