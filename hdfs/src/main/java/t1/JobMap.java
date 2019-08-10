package t1;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

// Mapper 입력키 - 행번호 /입력값 - 행의 글자/출력키 - 글자 /출력값 - 1
public class JobMap extends Mapper<LongWritable, Text, Text, IntWritable>{

	private final static IntWritable one = new IntWritable(1);
	
	
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		
		//열 구분하여 데이터 만들기
		String[] values =value.toString().split(",");
//		System.out.println("values ="+values);
		// 열 중에 첫번째 값인 년도 구하기
		String strKey = values[1];
		//년도를 자료형으로 변환
		Text textKey = new Text();
		textKey.set(strKey);
		
		//IntWritable은 위에 있음
		
		//값 보내기
		context.write(textKey, one);
	}
	
}
